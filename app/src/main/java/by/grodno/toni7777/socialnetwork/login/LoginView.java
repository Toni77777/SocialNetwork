package by.grodno.toni7777.socialnetwork.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface LoginView extends MvpView {

    void showLoginForm();

    void showError();

    void showLoading();

    void loginSuccess();

}
