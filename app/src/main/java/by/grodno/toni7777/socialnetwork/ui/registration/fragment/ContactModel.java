package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.registration.Profile;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import okhttp3.RequestBody;
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

        Map<String, String> map = new HashMap<>();
        map.put("name", profile.getName());
        map.put("lastName", profile.getSurname());
        map.put("login", profile.getLogin());
        map.put("password", profile.getPassword());
        map.put("email", profile.getSex());
//        map.put("sex", profile.getSex());
        map.put("sex", "1");
        map.put("bday", profile.getDateBirth());

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(map)).toString());
        Observable<ResponseDTO> registrationObservable = mNetworkAPI.registration(body);

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
                            Log.e("Error", "Error autorization" + throwable.toString());
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