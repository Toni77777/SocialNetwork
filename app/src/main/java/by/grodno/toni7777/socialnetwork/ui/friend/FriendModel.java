package by.grodno.toni7777.socialnetwork.ui.friend;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.RealmList;
import rx.Observable;
import rx.Subscription;

public class FriendModel implements BaseModel, FriendMVP.Model {

    private ModelListener<List<PostDVO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public FriendModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                      ModelListener<List<PostDVO>> listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
    }

    @Override
    public void loadPosts(long friendId, int offset) {
        Observable<WallDTO> postsObservable = mNetworkAPI.getGroupPosts(friendId, offset, Constants.SMALL_LIMIT, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = postsObservable
                .map(wall -> {
                    WallDSO wallDSO = ConverterDTOtoDSO.converteDTOtoDSO(wall);
                    RealmList<PostDSO> posts = wallDSO.getPostDSO();
                    return ConverterDTOtoDVO.converteDSOtoDVO(posts);
                })
                .compose(RxUtil.<List<PostDVO>>applySchedulers())
                .subscribe(
                        posts -> {
                            mListener.loadNext(posts);
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
