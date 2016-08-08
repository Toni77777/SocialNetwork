package by.grodno.toni7777.socialnetwork.people;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.PeopleDTO;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class PeopleModel extends BaseModel<PeopleDTO> {

    private ModelListener<PeopleDTO> mListener;
    private Subscription mSubscription;

    public PeopleModel(ModelListener<PeopleDTO> listener) {
        this.mListener = listener;
    }

    @Override
    protected void loadData(Observable<PeopleDTO> observable) {
        unsubscribe();
        mSubscription = observable
                .doOnNext(this::saveInCache)
                .compose(RxUtil.<PeopleDTO>applySchedulers())
                .subscribe(
                        people -> {
                            mListener.loadNext(people);
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

    private void saveInCache(PeopleDTO peopleDTO) {
        // TODO Put in cache to do next step
    }


    @Override
    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
