package by.grodno.toni7777.socialnetwork.ui.login;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.NotificationDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.Realm;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.CLIENT_ID_VALUE;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.GRAND_TYPE_VALUE;

public class LoginModel implements BaseModel, LoginMVP.Model {

    private BaseListener mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public LoginModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO, BaseListener listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mDatabaseDAO = databaseDAO;
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
                            Log.e("Token", authorization.getAccessToken().toString());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                            Log.e("Logn Error", "Login Error" + throwable.toString());
                        },
                        () -> {
                            unsubscribe();
                            sendNotificationToken();
                        });
    }

    @Override
    public void sendNotificationToken() {
        Log.e("Send notification", "Send notification");
        String token = FirebaseInstanceId.getInstance().getToken();
        NotificationDTO notificationDTO = new NotificationDTO(token);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new Gson().toJson(notificationDTO)));
        Observable<ResponseDTO> notifTokenObservable = mNetworkAPI.sentFCMToken(body, mPreferences.getAccessToken());

        mSubscription = notifTokenObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                        },
                        throwable -> {
                            unsubscribe();
                            loadProfileInfo();
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
                .map(ConverterDTOtoDSO::converteDTOtoDSO)
                .doOnNext(profileDSO -> mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), profileDSO))
                .compose(RxUtil.<ProfileDSO>applySchedulers())
                .subscribe(
                        profile -> {
                            mPreferences.setUserId(profile.getId());
                            mPreferences.setUserAvatar(profile.getAvatar());
                            mPreferences.setUserFullName(profile.getName(), profile.getSurname());
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
