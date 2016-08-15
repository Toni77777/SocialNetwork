package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.registration.Profile;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class ContactModel implements BaseModel, ContactMVP.ContactModel {

    private BaseListener mListener;
    private Subscription mSubscription;
    private SocialNetworkAPI mNetworkAPI;

    public ContactModel(SocialNetworkAPI networkAPI, BaseListener listener) {
        mNetworkAPI = networkAPI;
        mListener = listener;
    }

    @Override
    public void registration(Profile profile) {
        Observable<ResponseDTO> registrationObservable = mNetworkAPI.registration(null);

        mSubscription = registrationObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mListener.onLoadCompleted();
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                        });
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
