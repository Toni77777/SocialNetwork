package by.grodno.toni7777.socialnetwork.login;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class LoginModel extends BaseModel<AuthorizationDTO> {

    private ModelListener<AuthorizationDTO> mListener;
    private Subscription mSubscription;

    public LoginModel(ModelListener<AuthorizationDTO> listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<AuthorizationDTO> observable) {
        mSubscription = observable
                .compose(RxUtil.<AuthorizationDTO>applySchedulers())
                .subscribe(
                        user -> {
                            mListener.loadNext(user);
                            Log.e("User", user.toString());
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
