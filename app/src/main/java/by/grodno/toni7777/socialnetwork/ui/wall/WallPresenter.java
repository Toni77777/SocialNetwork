package by.grodno.toni7777.socialnetwork.ui.wall;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.base.event.LikeEvent;
import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.wall.listener.LikeListener;
import by.grodno.toni7777.socialnetwork.ui.wall.listener.RemovePostListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class WallPresenter extends MvpBasePresenter<WallMVP.View>
        implements ModelListener<List<PostDVO>>, MvpPresenter<WallMVP.View>, WallMVP.Presenter, RemovePostListener, LikeListener {

    private final WallModel mModel;
    private boolean mForceRefresh;

    @Inject
    public WallPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        mModel = new WallModel(socialNetworkAPI, loginPreferences, databaseDAO, this, this, this);
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadPosts(offset);
    }

    public void removePost(long posId) {
        mModel.removePost(posId);
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
            getView().setData(post);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }

    @Override
    public void onRemoveCompleted(long removedPostId) {
        if (isViewAttached()) {
            getView().removePostAfterDeleteServer(removedPostId);
        }
    }

    @Override
    public void removeGetError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, false);
        }
    }

    @Override
    public void sendLike(LikeEvent event) {
        mModel.sendLike(event);
    }

    @Override
    public void sendLikeCompleted(PostDVO post) {
        if (isViewAttached()) {
            getView().postLiked(post);
        }
    }

    @Override
    public void sendLikeError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, false);
        }
    }
}
