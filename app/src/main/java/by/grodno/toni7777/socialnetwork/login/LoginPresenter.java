package by.grodno.toni7777.socialnetwork.login;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.QueryProperties;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import rx.Observable;

public class LoginPresenter extends MvpBasePresenter<LoginView>
        implements ModelListener<UserLoginDTO>, MvpPresenter<LoginView> {

    private LoginModel mModel = new LoginModel(this);
    private SocialNetworkAPI mSocialNetworkAPI;

    @Inject
    public LoginPresenter(SocialNetworkAPI socialNetworkAPI) {
        mSocialNetworkAPI = socialNetworkAPI;
    }

    public void authorization(String login, String password) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        Observable<UserLoginDTO> observable = mSocialNetworkAPI.loginRequest(QueryProperties.GRAND_TYPE_VALUE, QueryProperties.CLIENT_ID_VALUE, login, password);
        mModel.loadData(observable);
    }

    @Override
    public void loadCompleted() {
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
    public void loadNext(UserLoginDTO data) {
        // TODO need rupdate to save data to strorage (in model), and refactor code, delete this method
    }
}
