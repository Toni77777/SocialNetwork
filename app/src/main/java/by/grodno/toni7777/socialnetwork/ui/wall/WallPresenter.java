package by.grodno.toni7777.socialnetwork.ui.wall;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class WallPresenter extends MvpBasePresenter<WallMVP.WallView>
        implements ModelListener<List<PostDTO>>, MvpPresenter<WallMVP.WallView>, WallMVP.WallPresenter {

    private final WallModel mModel;
    private boolean mForceRefresh;

    @Inject
    public WallPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new WallModel(socialNetworkAPI, loginPreferences, this);
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
    public void loadNext(List<PostDTO> post) {
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
}
