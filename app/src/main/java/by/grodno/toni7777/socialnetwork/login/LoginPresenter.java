package by.grodno.toni7777.socialnetwork.login;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import rx.Observable;

public class LoginPresenter extends MvpBasePresenter<LoginView>
        implements ModelListener<AuthorizationDTO>, MvpPresenter<LoginView> {

    private LoginModel mModel;
    private SocialNetworkAPI mSocialNetworkAPI;

    @Inject
    public LoginPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mSocialNetworkAPI = socialNetworkAPI;
        mModel = new LoginModel(loginPreferences, this);
    }

    public void authorization(String login, String password) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        Observable<AuthorizationDTO> observable = NetworkServiceTest.netLogin().loginRequest(GRAND_TYPE_VALUE, CLIENT_ID_VALUE, login, password);
//        Observable<AuthorizationDTO> observable = mSocialNetworkAPI.loginRequest(QueryProperties.GRAND_TYPE_VALUE, QueryProperties.CLIENT_ID_VALUE, login, password);
        mModel.loadData(observable);
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

    @Override
    public void loadNext(AuthorizationDTO data) {
        // TODO need rupdate to save data to strorage (in model), and refactor code, delete this method
//        mLoginPreferences.setAccessToken(data.getAccessToken());
    }

    public void loadProfileInfo() {

    }
}
