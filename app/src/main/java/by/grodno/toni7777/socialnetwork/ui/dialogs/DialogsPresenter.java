package by.grodno.toni7777.socialnetwork.ui.dialogs;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class DialogsPresenter extends MvpBasePresenter<DialogsMVP.View>
        implements ModelListener<List<DialogDTO>>, MvpPresenter<DialogsMVP.View>, DialogsMVP.Presenter {

    private final DialogsModel mModel;
    private boolean mForceRefresh;

    @Inject
    public DialogsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new DialogsModel(socialNetworkAPI, loginPreferences, this);
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.loadDialogs(offset);
    }

    @Override
    public void loadNext(List<DialogDTO> data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }
}
