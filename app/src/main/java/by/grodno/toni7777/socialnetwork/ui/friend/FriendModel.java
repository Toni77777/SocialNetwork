package by.grodno.toni7777.socialnetwork.ui.friend;

import android.util.Log;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import by.grodno.toni7777.socialnetwork.ui.friend.listener.FriendProfileListener;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ConverterDSOtoDVO;
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
    private FriendProfileListener mProfileListener;

    public FriendModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                       ModelListener<List<PostDVO>> listener, FriendProfileListener profileListener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
        mProfileListener = profileListener;
    }

    @Override
    public void loadUserInfo(long friendId) {
        Observable<ProfileDTO> postsObservable = mNetworkAPI.getUserProfile(friendId, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = postsObservable
                .map(ConverterDTOtoDSO::converteDTOtoDSO)
                .map(ConverterDSOtoDVO::converteDTOtoDSO)
                .compose(RxUtil.<ProfileDVO>applySchedulers())
                .subscribe(
                        profile -> {
                            Log.e("Friend", "Friend profile = " + profile);
                            mProfileListener.onProfileLoadCompleted(profile);
                            loadPosts(friendId, Constants.START_LOAD);
                        },
                        throwable -> {
                            Log.e("Friend", "Error" + throwable);
                            mProfileListener.loadProfileError(throwable);
                            unsubscribe();
                        },
                        () -> {
                            unsubscribe();
                        }
                );
    }

    @Override
    public void loadPosts(long friendId, int offset) {
        Observable<WallDTO> postsObservable = mNetworkAPI.getPost(friendId, offset, Constants.SMALL_LIMIT, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = postsObservable
                .map(wall -> {
                    Log.e("Friend", "Wall = " + wall.toString());
                    WallDSO wallDSO = ConverterDTOtoDSO.converteDTOtoDSO(wall);
                    RealmList<PostDSO> posts = wallDSO.getPostDSO();
                    return ConverterDTOtoDVO.converteDSOtoDVO(posts);
                })
                .compose(RxUtil.<List<PostDVO>>applySchedulers())
                .subscribe(
                        posts -> {
                            Log.e("Friend", "Friend posts" + posts.toString());
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
