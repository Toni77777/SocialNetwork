package by.grodno.toni7777.socialnetwork.wall;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class WallModel extends BaseModel<WallDTO> {

    private ModelListener<List<PostDTO>> mListener;
    private Subscription mSubscription;

    public WallModel(ModelListener<List<PostDTO>> listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<WallDTO> observable) {
        unsubscribe();
        mSubscription = observable
                .doOnNext(this::saveInCache)
                .compose(RxUtil.<WallDTO>applySchedulers())
                .subscribe(
                        wallDTO -> {
                            mListener.loadNext(wallDTO.getPosts());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        }
                );
    }

    private void saveInCache(WallDTO wallDTO) {
        // TODO Put in cache to do next step
    }


    @Override
    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
