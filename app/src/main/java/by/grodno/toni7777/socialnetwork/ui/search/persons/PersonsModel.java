package by.grodno.toni7777.socialnetwork.ui.search.persons;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonsDTO;

import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.search.persons.listener.FriendListener;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class PersonsModel implements BaseModel, PersonsMVP.Model {

    private ModelListener<List<PersonDTO>> mListener;
    private FriendListener mFriendListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public PersonsModel(ModelListener<List<PersonDTO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, FriendListener friendListener) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
        mFriendListener = friendListener;
    }

    @Override
    public void findPersons(String fullNameSearch, int offset) {
        Observable<PersonsDTO> postsObservable = mNetworkAPI.findPersons(fullNameSearch, offset, Constants.HIGHT_LIMIT, mPreferences.getAccessToken());

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
    public void addPersonToFriend(Long userId) {
        Observable<ResponseDTO> postsObservable = mNetworkAPI.addPersonToFriend(userId, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mFriendListener.addNewFriendCompleted(userId);
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mFriendListener.addNewFriendError(throwable);
                        },
                        () -> {
                            unsubscribe();
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
