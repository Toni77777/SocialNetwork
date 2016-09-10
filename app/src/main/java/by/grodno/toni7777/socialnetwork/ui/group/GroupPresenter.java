package by.grodno.toni7777.socialnetwork.ui.group;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class GroupPresenter extends MvpBasePresenter<GroupMVP.View>
        implements ModelListener<List<PostDVO>>, MvpPresenter<GroupMVP.View>, GroupMVP.Presenter {

    private final GroupModel mModel;
    private boolean mForceRefresh;

    @Inject
    public GroupPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new GroupModel(socialNetworkAPI, loginPreferences, this);
    }

    @Override
    public void loadDataWithOffset(long groupId, boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadPosts(groupId, offset);
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

    public void loadGroupInfo(long groupId) {
        mModel.loadGroupInfo(groupId);
    }
}
