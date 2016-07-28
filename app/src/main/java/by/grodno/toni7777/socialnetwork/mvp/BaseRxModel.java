package by.grodno.toni7777.socialnetwork.mvp;

import rx.Observable;

public abstract class BaseRxModel<M> {

    protected abstract void loadRxData(Observable<M> observable);

    protected abstract void rxUnSubscribe();
}
