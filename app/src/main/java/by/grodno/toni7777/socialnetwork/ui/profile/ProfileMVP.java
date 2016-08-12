package by.grodno.toni7777.socialnetwork.ui.profile;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;


public interface ProfileMVP {

    interface ProfileModel {

        void loadProfileInfo();

    }

    interface ProfileView extends MvpView {

        void showProfileForm();

        void showError();

        void showLoading();

        void getProfileSuccess(ProfileDVO profile);

    }

    interface ProfilePresenter {

        void getProfileInfo();

    }
}
