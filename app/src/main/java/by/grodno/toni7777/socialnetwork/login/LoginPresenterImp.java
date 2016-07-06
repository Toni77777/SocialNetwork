package by.grodno.toni7777.socialnetwork.login;

import by.grodno.toni7777.socialnetwork.network.NetworkService;
import by.grodno.toni7777.socialnetwork.test.UserLogin;
import rx.Observable;
import rx.Observer;
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

        subscription = loginObservable.subscribe(new Observer<UserLogin>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loginView.loginError(e);
            }

            @Override
            public void onNext(UserLogin userLogin) {
                loginView.loginSuccess(userLogin);
            }

        });
    }


    @Override
    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
