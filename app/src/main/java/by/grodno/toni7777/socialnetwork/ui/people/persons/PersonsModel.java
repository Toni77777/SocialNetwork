package by.grodno.toni7777.socialnetwork.ui.people.persons;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonsDTO;

import static by.grodno.toni7777.socialnetwork.util.Constants.LIMIT;

import by.grodno.toni7777.socialnetwork.ui.model.PersonDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PersonsDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

public class PersonsModel implements BaseModel, PersonsMVP.PersonsModel {

    private ModelListener<List<PersonDTO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public PersonsModel(ModelListener<List<PersonDTO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
    }

    @Override
    public void findPersons(String fullNameSearch, int offset) {
        Observable<PersonsDTO> postsObservable = mNetworkAPI.findPersons(fullNameSearch, offset, 10, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<PersonsDTO>applySchedulers())
                .subscribe(
                        persons -> {
                            mListener.loadNext(persons.getPersons());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        }
                );
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
