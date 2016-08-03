package by.grodno.toni7777.socialnetwork.wall;

import java.util.List;
import java.util.concurrent.TimeUnit;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class WallModel extends BaseModel<WallDTO> {

    private ModelListener<List<PostDTO>> listener;
    private Subscription subscription;

    public WallModel(ModelListener<List<PostDTO>> listener) {
        this.listener = listener;
    }

    @Override
    protected void loadData(Observable<WallDTO> observable) {
        unsubscribe();
        subscription = observable
                .doOnNext(this::saveInCache)
                .compose(RxUtil.<WallDTO>applySchedulers())
                .subscribe(new Subscriber<WallDTO>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                        listener.loadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        unsubscribe();
                        listener.loadError(e);
                    }

                    @Override
                    public void onNext(WallDTO o) {
                        listener.loadNext(o.getPosts());
                    }
                });
    }

    private void saveInCache(WallDTO wallDTO) {
        // TODO Put in cache to do next step
    }


    @Override
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
