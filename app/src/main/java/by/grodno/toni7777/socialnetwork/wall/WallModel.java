package by.grodno.toni7777.socialnetwork.wall;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import static by.grodno.toni7777.socialnetwork.util.RxUtil.*;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class WallModel extends BaseModel {

    private ModelListener listener;
    private Subscription subscription;

    public WallModel(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    protected void loadRxData(Observable observable) {
        rxUnSubscribe();
        subscription = observable
//                .delay(3, TimeUnit.SECONDS) // Delay for emulated hard task load
                .compose(applySchedulers())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        rxUnSubscribe();
                        listener.loadRxCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        rxUnSubscribe();
                        listener.loadRxError(e);
                    }

                    @Override
                    public void onNext(Object o) {
                        listener.loadRxNext(((WallDTO) o).getPosts());
                    }
                });
    }

    @Override
    protected void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
