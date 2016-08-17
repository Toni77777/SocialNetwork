package by.grodno.toni7777.socialnetwork.ui.people;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class FriendsPresenter extends MvpBasePresenter<FriendsMVP.FriendsView>
        implements ModelListener<List<FriendDVO>>, MvpPresenter<FriendsMVP.FriendsView>, FriendsMVP.FriendsPresenter {

    private final FriendsModel mModel;
    private boolean mForceRefresh;

    @Inject
    public FriendsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        mModel = new FriendsModel(this, loginPreferences, socialNetworkAPI, databaseDAO);
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadFriends(offset);
    }

    @Override
    public void loadNext(List<FriendDVO> data) {
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
