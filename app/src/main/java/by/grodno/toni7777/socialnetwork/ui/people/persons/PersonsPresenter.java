package by.grodno.toni7777.socialnetwork.ui.people.persons;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class PersonsPresenter extends MvpBasePresenter<PersonsMVP.PersonsView>
        implements ModelListener<List<PersonDTO>>, MvpPresenter<PersonsMVP.PersonsView>, PersonsMVP.PersonsPresenter {

    private final PersonsModel mModel;
    private boolean mForceRefresh;

    @Inject
    public PersonsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new PersonsModel(this, loginPreferences, socialNetworkAPI);
    }


    @Override
    public void loadDataWithOffset(String fullNameSearch, boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        mModel.findPersons(fullNameSearch, offset);
    }

    @Override
    public void loadNext(List<PersonDTO> data) {
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
