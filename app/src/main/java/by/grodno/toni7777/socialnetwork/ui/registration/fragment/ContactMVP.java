package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.grodno.toni7777.socialnetwork.network.model.ProfileRegistrationDTO;


public final class ContactMVP {

    interface Model {

        void registration(ProfileRegistrationDTO profile);

    }

    interface View extends MvpView {

        void showContactForm();

        void showError();

        void showLoading();

        void registrationSuccess();

    }

    interface Presenter {

        void registration(ProfileRegistrationDTO profile);

    }

    private ContactMVP() {
    }
}
