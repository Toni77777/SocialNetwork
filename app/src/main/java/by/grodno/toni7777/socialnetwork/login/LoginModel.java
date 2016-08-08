package by.grodno.toni7777.socialnetwork.login;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class LoginModel extends BaseModel<UserLoginDTO> {

    private ModelListener<UserLoginDTO> mListener;
    private Subscription mSubscription;

    public LoginModel(ModelListener<UserLoginDTO> listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<UserLoginDTO> observable) {
        mSubscription = observable
                .compose(RxUtil.<UserLoginDTO>applySchedulers())
                .subscribe(
                        user -> {
                            mListener.loadNext(user);
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
