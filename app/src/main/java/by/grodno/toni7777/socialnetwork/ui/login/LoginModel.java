package by.grodno.toni7777.socialnetwork.ui.login;


import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.CLIENT_ID_VALUE;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.GRAND_TYPE_VALUE;

public class LoginModel implements BaseModel, LoginMVP.LoginModel {

    private BaseListener mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public LoginModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, BaseListener listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        this.mListener = listener;
    }

    @Override
    public void getAccessToken(String login, String password) {
        Observable<AuthorizationDTO> tokenObservable = mNetworkAPI.loginRequest(GRAND_TYPE_VALUE, CLIENT_ID_VALUE, login, password);

        mSubscription = tokenObservable
                .compose(RxUtil.<AuthorizationDTO>applySchedulers())
                .subscribe(
                        authorization -> {
                            mPreferences.setAccessToken(authorization.getAccessToken());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                            Log.e("Error", "Login error" + throwable.toString());
                        },
                        () -> {
                            unsubscribe();
                            loadProfileInfo();
                        });
    }

    @Override
    public void loadProfileInfo() {
        Observable<ProfileDTO> profileObservable = mNetworkAPI.getProfileInfo(mPreferences.getAccessToken());

        mSubscription = profileObservable
                .compose(RxUtil.<ProfileDTO>applySchedulers())
                .subscribe(
                        profile -> {
                            mPreferences.setUserId(profile.getUser().getId());
                            mPreferences.setUserAvatar(profile.getUser().getAvatar());
                            mPreferences.setUserFullName(profile.getUser().getName(), profile.getUser().getSurname());
                        },
                        throwable -> {
                            unsubscribe();
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        });
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
