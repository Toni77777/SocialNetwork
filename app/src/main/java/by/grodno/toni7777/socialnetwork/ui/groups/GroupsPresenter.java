package by.grodno.toni7777.socialnetwork.ui.groups;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.ui.model.GroupDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class GroupsPresenter extends MvpBasePresenter<GroupsMVP.View>
        implements ModelListener<List<GroupDVO>>, MvpPresenter<GroupsMVP.View>, GroupsMVP.Presenter {

    private final GroupsModel mModel;
    private boolean mForceRefresh;

    @Inject
    public GroupsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        mModel = new GroupsModel(this, loginPreferences, socialNetworkAPI, databaseDAO);
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadGroups(offset);
    }

    @Override
    public void loadNext(List<GroupDVO> data) {
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
