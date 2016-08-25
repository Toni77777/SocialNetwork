package by.grodno.toni7777.socialnetwork.ui.profile;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.ui.profile.listener.ProfileListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class ProfilePresenter extends MvpBasePresenter<ProfileMVP.View>
        implements ProfileListener, MvpPresenter<ProfileMVP.View>, ProfileMVP.Presenter {

    private final ProfileModel mModel;

    public ProfilePresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        mModel = new ProfileModel(socialNetworkAPI, loginPreferences, databaseDAO, this);
    }

    @Override
    public void getProfileInfo() {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.loadProfileInfo();
    }

    @Override
    public void onLoadCompleted(ProfileDVO profileDVO) {
        if (isViewAttached()) {
            getView().getProfileSuccess(profileDVO);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError();
        }
    }

}
