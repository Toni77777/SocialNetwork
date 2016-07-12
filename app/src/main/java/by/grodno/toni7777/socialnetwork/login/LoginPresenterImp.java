package by.grodno.toni7777.socialnetwork.login;

import javax.inject.Inject;
import javax.inject.Named;

import by.grodno.toni7777.socialnetwork.di.ApplicationModule;
import by.grodno.toni7777.socialnetwork.di.DaggerApplicationComponent;
import by.grodno.toni7777.socialnetwork.network.LoginService;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginPresenterImp implements LoginPresenter {

    @Inject
    @Named(LOGIN)
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
        subscription = loginService.loginRequest(login, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
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
