package by.grodno.toni7777.socialnetwork.mvp;

public interface BaseListener {

    void onLoadCompleted();

    void loadError(Throwable e);
}
