package by.grodno.toni7777.socialnetwork.ui.friend;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.friend.listener.FriendProfileListener;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class FriendPresenter extends MvpBasePresenter<FriendMVP.View>
        implements ModelListener<List<PostDVO>>, MvpPresenter<FriendMVP.View>, FriendMVP.Presenter, FriendProfileListener {

    private final FriendModel mModel;
    private boolean mForceRefresh;

    @Inject
    public FriendPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new FriendModel(socialNetworkAPI, loginPreferences, this, this);
    }

    @Override
    public void getProfileInfo(long userId, boolean forceRefresh) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mModel.loadUserInfo(userId);

    }

    @Override
    public void loadDataWithOffset(long friendId, boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadPosts(friendId, offset);
    }


    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void loadNext(List<PostDVO> post) {
        if (isViewAttached()) {
            getView().postLoaded(post);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }

    @Override
    public void onProfileLoadCompleted(ProfileDVO profile) {
        if (isViewAttached()) {
            getView().profileLoaded(profile);
        }
    }

    @Override
    public void loadProfileError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }
}
