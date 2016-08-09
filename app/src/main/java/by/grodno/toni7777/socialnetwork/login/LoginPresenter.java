package by.grodno.toni7777.socialnetwork.login;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import rx.Observable;

public class LoginPresenter extends MvpBasePresenter<LoginView>
        implements ModelListener<UserDTO>, MvpPresenter<LoginView> {

    private LoginModel mModel = new LoginModel(this);
    private SocialNetworkAPI mSocialNetworkAPI;
    private LoginPreferences mLoginPreferences;

    @Inject
    public LoginPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mSocialNetworkAPI = socialNetworkAPI;
        mLoginPreferences = loginPreferences;
    }

//    @Inject
//    public LoginPresenter(SocialNetworkAPI socialNetworkAPI) {
//        mSocialNetworkAPI = socialNetworkAPI;
//    }

    public void authorization(String login, String password) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        Observable<UserDTO> observable = NetworkServiceTest.netLogin().loginRequest(GRAND_TYPE_VALUE, CLIENT_ID_VALUE, login, password);
//        Observable<UserDTO> observable = mSocialNetworkAPI.loginRequest(QueryProperties.GRAND_TYPE_VALUE, QueryProperties.CLIENT_ID_VALUE, login, password);
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
    public void loadNext(UserDTO data) {
        // TODO need rupdate to save data to strorage (in model), and refactor code, delete this method
        mLoginPreferences.setAccessToken(data.getAccessToken());
    }
}
