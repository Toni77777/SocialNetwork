package by.grodno.toni7777.socialnetwork.mvp;

public interface ModelListener<M> {

    void loadRxCompleted();

    void loadRxNext(M data);

    void loadRxError(Throwable e);

}
