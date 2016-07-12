package by.grodno.toni7777.socialnetwork.login;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.network.NetworkService;
import by.grodno.toni7777.socialnetwork.test.UserLogin;
import rx.Observable;
import rx.Subscription;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    private NetworkService networkService;
    private Subscription subscription;

    public LoginPresenterImp(LoginView loginView, NetworkService networkService) {
        this.loginView = loginView;
        this.networkService = networkService;
    }

    @Override
    public void loginRequest(String login, String password) {
        Observable<UserLogin> loginObservable = (Observable<UserLogin>)
                networkService.getPreparedObservable(networkService.getLoginService().loginRequest(login, password));

        subscription = loginObservable.subscribe(
                userLogin -> loginView.loginSuccess(userLogin),
                throwable -> loginView.loginError(throwable));
    }

    @Override
    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
