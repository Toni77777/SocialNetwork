package by.grodno.toni7777.socialnetwork.mvp;

import rx.Observable;

public abstract class BaseModel<M> {

    protected abstract void loadData(Observable<M> observable);

    protected abstract void unsubscribe();
}
