package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import android.util.Log;

import com.google.gson.Gson;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ProfileRegistrationDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;

public class ContactModel implements BaseModel, ContactMVP.Model {

    private BaseListener mListener;
    private Subscription mSubscription;
    private SocialNetworkAPI mNetworkAPI;

    public ContactModel(SocialNetworkAPI networkAPI, BaseListener listener) {
        mNetworkAPI = networkAPI;
        mListener = listener;
    }

    @Override
    public void registration(ProfileRegistrationDTO profile) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse(Constants.CONTENT_TYPE_APPLICATION_JSON), (new Gson().toJson(profile)));
        Observable<ResponseDTO> registrationObservable = mNetworkAPI.registration(body);
        mSubscription = registrationObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            Log.e("Login", response.toString());
                            if (response.isSuccess()) {
                                mListener.onLoadCompleted();
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                            Log.e("Error", "Error autorization" + throwable.toString());
                            Log.e("Error", "Error autorization" + throwable.getMessage());
                            Log.e("Error", "Error autorization" + throwable.getLocalizedMessage());
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
