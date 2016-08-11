package by.grodno.toni7777.socialnetwork.ui.login;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
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
        Observable<AuthorizationDTO> tokenObservable = NetworkServiceTest.netLogin().loginRequest(GRAND_TYPE_VALUE, CLIENT_ID_VALUE, login, password);
//        Observable<AuthorizationDTO> observable = mSocialNetworkAPI.loginRequest(QueryProperties.GRAND_TYPE_VALUE, QueryProperties.CLIENT_ID_VALUE, login, password);

        mSubscription = tokenObservable
                .compose(RxUtil.<AuthorizationDTO>applySchedulers())
                .subscribe(
                        authorization -> {
                            mPreferences.setAccessToken(authorization.getAccessToken());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            loadProfileInfo();
                        });
    }

    @Override
    public void loadProfileInfo() {
//        Observable<ProfileDTO> observable = NetworkServiceTest.netLogin().loginRequest(GRAND_TYPE_VALUE, CLIENT_ID_VALUE, login, password);
        Observable<ProfileDTO> profileObservable = NetworkServiceTest.netProfile().getProfileInfo(mPreferences.getAccessToken());

        mSubscription = profileObservable
                .compose(RxUtil.<ProfileDTO>applySchedulers())
                .subscribe(
                        profile -> {
                            Log.e("Profile", profile.toString());
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
