package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.grodno.toni7777.socialnetwork.ui.registration.Profile;

public interface ContactMVP {

    interface Model {

        void registration(Profile profile);

    }

    interface View extends MvpView {

        void showContactForm();

        void showError();

        void showLoading();

        void registrationSuccess();

    }

    interface Presenter {

        void registration(Profile profile);

    }
}
