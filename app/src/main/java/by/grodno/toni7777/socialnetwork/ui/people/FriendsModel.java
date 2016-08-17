package by.grodno.toni7777.socialnetwork.ui.people;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

import static by.grodno.toni7777.socialnetwork.util.Constants.LIMIT;

public class FriendsModel implements BaseModel, FriendsMVP.FriendsModel {

    private ModelListener<List<FriendDTO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public FriendsModel(ModelListener<List<FriendDTO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, DatabaseDAOImp databaseDAO) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
        mDatabaseDAO = databaseDAO;
    }

    @Override
    public void loadFriends(int offset) {
        Observable<FriendsDTO> postsObservable = mNetworkAPI.getFriends(mPreferences.getUserId(), offset, LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
//                .map(new Func1<WallDTO, Object>() {
//
//                    @Override
//                    public Object call(WallDTO wallDTO) {
//                        return null;
//                    }
//                })
//                .doOnNext(this::saveInCache)
                .compose(RxUtil.<FriendsDTO>applySchedulers())
                .subscribe(
                        friends -> {
                            mListener.loadNext(friends.getFriends());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        }
                );
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
