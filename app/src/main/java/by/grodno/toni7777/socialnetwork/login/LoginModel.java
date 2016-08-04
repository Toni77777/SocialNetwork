package by.grodno.toni7777.socialnetwork.login;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class LoginModel extends BaseModel<UserLoginDTO> {

    private ModelListener<UserLoginDTO> listener;
    private Subscription subscription;

    public LoginModel(ModelListener<UserLoginDTO> listener) {
        this.listener = listener;
    }

    @Override
    protected void loadData(Observable<UserLoginDTO> observable) {
        subscription = observable
                .compose(RxUtil.<UserLoginDTO>applySchedulers())
                .subscribe(
                        user -> {
                            listener.loadNext(user);
                        },
                        throwable -> {
                            unsubscribe();
                            listener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            listener.loadCompleted();
                        });
    }

    @Override
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
