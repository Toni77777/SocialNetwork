package by.grodno.toni7777.socialnetwork.ui.restore;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.restore.listener.RestoreListener;

public class RestorePresenter extends MvpBasePresenter<RestoreMVP.View>
        implements MvpPresenter<RestoreMVP.View>, RestoreMVP.Presenter, RestoreListener {

    private final RestoreModel mModel;

    @Inject
    public RestorePresenter(SocialNetworkAPI socialNetworkAPI) {
        mModel = new RestoreModel(socialNetworkAPI, this);
    }

    @Override
    public void restorePassword(String email) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.restorePassword(email);
    }

    @Override
    public void onRestoreSuccess() {
        if (isViewAttached()) {
            getView().onRestoreSuccess();
        }
    }

    @Override
    public void restoreError(Throwable throwable) {
        if (isViewAttached()) {
            getView().showErrors(throwable);
        }
    }
}
