package by.grodno.toni7777.socialnetwork.ui.profile;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

public class ProfileViewState implements ViewState<ProfileMVP.ProfileView> {

    private static final int STATE_SHOW_PROFILE_FORM = 4;
    private static final int STATE_SHOW_LOADING = 5;
    private static final int STATE_SHOW_ERROR = 6;

    int state = STATE_SHOW_PROFILE_FORM;

    public void setShowProfileForm() {
        state = STATE_SHOW_PROFILE_FORM;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    @Override
    public void apply(ProfileMVP.ProfileView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;

            case STATE_SHOW_ERROR:
                view.showError();
                break;

            case STATE_SHOW_PROFILE_FORM:
                view.showProfileForm();
                break;
        }
    }
}
