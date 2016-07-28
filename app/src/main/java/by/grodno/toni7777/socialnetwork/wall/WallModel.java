package by.grodno.toni7777.socialnetwork.wall;

import java.util.concurrent.TimeUnit;

import by.grodno.toni7777.socialnetwork.mvp.BaseRxModel;
import by.grodno.toni7777.socialnetwork.mvp.RxModelListener;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WallModel extends BaseRxModel {

    private RxModelListener listener;
    private Subscription subscription;

    public WallModel(RxModelListener listener) {
        this.listener = listener;
    }

    @Override
    protected void loadRxData(Observable observable) {
        rxUnSubscribe();
        subscription = observable
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
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
                        WallDTO wall = (WallDTO) o;
                        listener.loadRxNext(wall.getPosts());
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