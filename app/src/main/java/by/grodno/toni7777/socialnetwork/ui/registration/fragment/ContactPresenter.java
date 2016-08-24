package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.registration.Profile;


public class ContactPresenter extends MvpBasePresenter<ContactMVP.View>
        implements MvpPresenter<ContactMVP.View>, ContactMVP.Presenter, BaseListener {

    private final ContactModel mModel;

    @Inject
    public ContactPresenter(SocialNetworkAPI socialNetworkAPI) {
        mModel = new ContactModel(socialNetworkAPI, this);
    }

    @Override
    public void registration(Profile profile) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.registration(profile);
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().registrationSuccess();
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError();
        }
    }
}
