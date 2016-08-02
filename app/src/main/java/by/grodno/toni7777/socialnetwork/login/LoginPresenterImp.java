package by.grodno.toni7777.socialnetwork.login;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.di.ApplicationModule;
import by.grodno.toni7777.socialnetwork.di.DaggerApplicationComponent;
import by.grodno.toni7777.socialnetwork.network.LoginService;

import by.grodno.toni7777.socialnetwork.network.NetService;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginPresenterImp implements LoginPresenter {

    @Inject//remove
            LoginService loginService;
    private LoginView loginView;
    private Subscription subscription;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build()
                .inject(this);
    }

    @Override
    public void loginRequest(String login, String password) {
        subscription = loginService.loginRequest(BuildConfig.GRANT_TYPE_VALUE,
                BuildConfig.CLIENT_ID_VALUE, login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userLogin -> loginView.loginSuccess(userLogin),
                        throwable -> loginView.loginError(throwable));
    }

    @Override
    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
