package by.grodno.toni7777.socialnetwork.ui.search.groups;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class SearchGroupsPresenter extends MvpBasePresenter<SearchGroupsMVP.View>
        implements ModelListener<List<GroupDTO>>, MvpPresenter<SearchGroupsMVP.View>, SearchGroupsMVP.Presenter {

    private final SearchGroupsModel mModel;
    private boolean mForceRefresh;

    @Inject
    public SearchGroupsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new SearchGroupsModel(this, loginPreferences, socialNetworkAPI);
    }

    @Override
    public void loadDataWithOffset(String nameGroup, boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.findGroups(nameGroup, offset);
    }

    @Override
    public void loadNext(List<GroupDTO> data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }
}
