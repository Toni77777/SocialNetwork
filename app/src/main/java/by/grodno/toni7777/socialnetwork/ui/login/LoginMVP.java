package by.grodno.toni7777.socialnetwork.ui.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface LoginMVP {

    interface LoginModel {

        void getAccessToken(String login, String password);

        void loadProfileInfo();


    }

    interface LoginView extends MvpView {

        void showLoginForm();

        void showError();

        void showLoading();

        void loginSuccess();

    }

    interface LoginPresenter {

        void authorization(String login, String password);

    }
}
