package by.grodno.toni7777.socialnetwork.ui.friends;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.FriendDSO;
import by.grodno.toni7777.socialnetwork.database.model.FriendsDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendsDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDSOtoDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

import static by.grodno.toni7777.socialnetwork.util.Constants.MEDIUM_LIMIT;

public class FriendsModel implements BaseModel, FriendsMVP.Model {

    private ModelListener<List<FriendDVO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public FriendsModel(ModelListener<List<FriendDVO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, DatabaseDAOImp databaseDAO) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
        mDatabaseDAO = databaseDAO;
    }

    @Override
    public void loadFriends(int offset) {
        Observable<FriendsDTO> friendObservable = mNetworkAPI.getFriends(mPreferences.getUserId(), offset, MEDIUM_LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = friendObservable
                .map(ConverterDTOtoDSO::toFriendDSO)
                .doOnNext(friendsDSO -> saveInCache(friendsDSO, offset))
                .compose(RxUtil.<FriendsDSO>applySchedulers())
                .subscribe(
                        friends -> {
                        },
                        throwable -> {
                            unsubscribe();
                            readFriendsFromDB(offset);
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            readFriendsFromDB(offset);
                        }
                );
    }

    @Override
    public void saveInCache(FriendsDSO responseFriends, int offset) {
        RealmResults<FriendsDSO> friends = mDatabaseDAO.readAll(Realm.getDefaultInstance(), FriendsDSO.class);
        if (friends.size() == 0) { // have not wall object from DB
            Log.e("Write", "Write 1");
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseFriends);
        } else if (offset == 0) { // if refresh to new post
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseFriends);
            Log.e("Write", "Write 2");
        } else {
            Log.e("Write", "Write 3");
            RealmList<FriendDSO> response = responseFriends.getFriendsDSO();
            mDatabaseDAO.updateFriends(Realm.getDefaultInstance(), response);
        }
    }

    @Override
    public void readFriendsFromDB(int offset) {
        FriendsDSO friendsDSO = mDatabaseDAO.findFirst(Realm.getDefaultInstance(), FriendsDSO.class);
        if (friendsDSO == null) {
            throw new IllegalArgumentException("FriendsDSO null");
        } else {
            RealmList<FriendDSO> friends = friendsDSO.getFriendsDSO();
            if (friends == null) {
                throw new IllegalArgumentException("Friends from database null");
            }
            List<FriendDVO> readData = ConverterDSOtoDVO.toFriendDVO(friends);
            List<FriendDVO> result = new ArrayList<>();
            int size = readData.size();
            if (size >= (offset + MEDIUM_LIMIT - 1)) {
                result = readData.subList(offset, offset + MEDIUM_LIMIT - 1);
            } else {
                result = readData.subList(offset, size);
            }
            mListener.loadNext(result);
            mListener.onLoadCompleted();
        }
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}