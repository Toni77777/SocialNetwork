package by.grodno.toni7777.socialnetwork.login;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class LoginModel extends BaseModel<AuthorizationDTO> {

    private ModelListener<AuthorizationDTO> mListener;
    private Subscription mSubscription;
    private LoginPreferences mLoginPreferences;

    public LoginModel(LoginPreferences loginPreferences, ModelListener<AuthorizationDTO> listener) {
        mLoginPreferences = loginPreferences;
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<AuthorizationDTO> observable) {
        mSubscription = observable
                .compose(RxUtil.<AuthorizationDTO>applySchedulers())
                .subscribe(
                        user -> {
                            mListener.loadNext(user);
                            mLoginPreferences.setAccessToken(user.getAccessToken());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        });
    }

    protected void loadProfileInfo(Observable<ProfileDTO> observable) {
        mSubscription = observable
                .compose(RxUtil.<ProfileDTO>applySchedulers())
                .subscribe(
                        user -> {
//                            mListener.loadNext(user);
//                            mLoginPreferences.setAccessToken(user.getAccessToken());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        });
    }

    @Override
    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
