package by.grodno.toni7777.socialnetwork.ui.login;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.BaseListener;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class LoginPresenter extends MvpBasePresenter<LoginMVP.View>
        implements BaseListener, MvpPresenter<LoginMVP.View>, LoginMVP.Presenter {

    private final LoginModel mModel;

    @Inject
    public LoginPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        mModel = new LoginModel(socialNetworkAPI, loginPreferences, databaseDAO, this);
    }

    @Override
    public void authorization(String login, String password) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.getAccessToken(login, password);
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().loginSuccess();
        }
    }

    @Override
    public void loadError(Throwable error) {
        if (isViewAttached()) {
            getView().showError();
        }
    }
}
