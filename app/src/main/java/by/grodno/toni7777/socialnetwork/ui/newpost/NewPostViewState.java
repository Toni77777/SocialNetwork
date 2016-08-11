package by.grodno.toni7777.socialnetwork.ui.newpost;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

public class NewPostViewState implements ViewState<NewPostMVP.NewPostView> {

    final int STATE_SHOW_LOGIN_FORM = 0;
    final int STATE_SHOW_LOADING = 1;
    final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_LOGIN_FORM;

    public void setShowPostForm() {
        state = STATE_SHOW_LOGIN_FORM;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    @Override
    public void apply(NewPostMVP.NewPostView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;

            case STATE_SHOW_ERROR:
                view.showError();
                break;

            case STATE_SHOW_LOGIN_FORM:
                view.showPostForm();
                break;
        }
    }

}
