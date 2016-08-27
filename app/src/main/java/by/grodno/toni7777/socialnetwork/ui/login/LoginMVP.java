package by.grodno.toni7777.socialnetwork.ui.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

public final class LoginMVP {

    interface Model {

        void getAccessToken(String login, String password);

        void loadProfileInfo();

        void sendNotificationToken();

    }

    interface View extends MvpView {

        void showLoginForm();

        void showError();

        void showLoading();

        void loginSuccess();

        void showErrors(Throwable throwable);

    }

    interface Presenter {

        void authorization(String login, String password);

    }

    private LoginMVP() {
    }
}
