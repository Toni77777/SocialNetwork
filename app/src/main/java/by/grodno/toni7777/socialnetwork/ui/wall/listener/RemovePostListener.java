package by.grodno.toni7777.socialnetwork.ui.wall.listener;

public interface RemovePostListener {

    void onRemoveCompleted(long removedPostId);

    void removeGetError(Throwable e);
}
