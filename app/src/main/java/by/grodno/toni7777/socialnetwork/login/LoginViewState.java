package by.grodno.toni7777.socialnetwork.login;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

public class LoginViewState implements ViewState<LoginView> {

    final int STATE_SHOW_LOGIN_FORM = 0;
    final int STATE_SHOW_LOADING = 1;
    final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_LOGIN_FORM;

    @Override
    public void apply(LoginView view, boolean retained) {

        switch (state) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;

            case STATE_SHOW_ERROR:
                view.showError();
                break;

            case STATE_SHOW_LOGIN_FORM:
                view.showLoginForm();
                break;
        }
    }

    public void setShowLoginForm() {
        state = STATE_SHOW_LOGIN_FORM;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }
}
