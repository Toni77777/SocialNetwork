package by.grodno.toni7777.socialnetwork.mvp;

public interface ModelListener<M> {

    void loadCompleted();

    void loadNext(M data);

    void loadError(Throwable e);

}
