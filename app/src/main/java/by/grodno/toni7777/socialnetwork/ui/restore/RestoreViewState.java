package by.grodno.toni7777.socialnetwork.ui.restore;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

public class RestoreViewState implements ViewState<RestoreMVP.View> {

    private static final int STATE_SHOW_RESTORE_FORM = 0;
    private static final int STATE_SHOW_LOADING = 1;
    private static final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_RESTORE_FORM;

    public void setShowLoginForm() {
        state = STATE_SHOW_RESTORE_FORM;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    @Override
    public void apply(RestoreMVP.View view, boolean retained) {
        switch (state) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;

            case STATE_SHOW_ERROR:
                view.showError();
                break;

            case STATE_SHOW_RESTORE_FORM:
                view.showRestoreForm();
                break;
        }
    }
}
