package by.grodno.toni7777.socialnetwork.ui.profile;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;


public final class ProfileMVP {

    interface Model {

        void loadProfileInfo();

    }

    interface View extends MvpView {

        void showProfileForm();

        void showError();

        void showLoading();

        void getProfileSuccess(ProfileDVO profile);

    }

    interface Presenter {

        void getProfileInfo();

    }

    private ProfileMVP() {
    }
}
