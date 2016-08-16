package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import com.hannesdorfmann.mosby.mvp.MvpView;

import by.grodno.toni7777.socialnetwork.ui.registration.Profile;

public interface ContactMVP {

    interface ContactModel {

        void registration(Profile profile);

    }

    interface ContactView extends MvpView {

        void showContactForm();

        void showError();

        void showLoading();

        void registrationSuccess();

    }

    interface ContactPresenter {

        void registration(Profile profile);

    }
}
