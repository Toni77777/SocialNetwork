package by.grodno.toni7777.socialnetwork.login;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class LoginModel extends BaseModel<UserDTO> {

    private ModelListener<UserDTO> mListener;
    private Subscription mSubscription;

    public LoginModel(ModelListener<UserDTO> listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<UserDTO> observable) {
        mSubscription = observable
                .compose(RxUtil.<UserDTO>applySchedulers())
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
