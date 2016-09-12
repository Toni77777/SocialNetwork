package by.grodno.toni7777.socialnetwork.ui.friend;

import android.util.Log;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ChatIdDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import by.grodno.toni7777.socialnetwork.ui.friend.listener.ChatListener;
import by.grodno.toni7777.socialnetwork.ui.friend.listener.FriendProfileListener;
import by.grodno.toni7777.socialnetwork.ui.friend.listener.RemoveFriendListener;
import by.grodno.toni7777.socialnetwork.ui.model.ChatIdDVO;
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
    private RemoveFriendListener mRemoveFriendListener;
    private ChatListener mChatListener;

    public FriendModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                       ModelListener<List<PostDVO>> listener, FriendProfileListener profileListener,
                       RemoveFriendListener removeFriendListener, ChatListener chatListener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
        mProfileListener = profileListener;
        mRemoveFriendListener = removeFriendListener;
        mChatListener = chatListener;
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
    public void removeUserFromFriends(long friendId) {
        Observable<ResponseDTO> removeObservable = mNetworkAPI.removeUserFormFriends(friendId, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = removeObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mRemoveFriendListener.onRemoveCompleted();
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mRemoveFriendListener.removeGetError(throwable);
                        },
                        () -> {
                            unsubscribe();
                        }
                );
    }

    public void getChatId(long friendId) {
        Observable<ChatIdDTO> chatObservable = mNetworkAPI.getChatId(friendId, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = chatObservable
                .map(ConverterDTOtoDVO::toChatId)
                .compose(RxUtil.<ChatIdDVO>applySchedulers())
                .subscribe(
                        chatId -> {
                            mChatListener.onChatLoadCompleted(chatId);
                        },
                        throwable -> {
                            unsubscribe();
                            mChatListener.loadChatIdError(throwable);
                        },
                        () -> {
                            unsubscribe();
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
