package by.grodno.toni7777.socialnetwork.base.event;

public class PostEvent {

    private final long mPostId;

    public PostEvent(long postId) {
        mPostId = postId;
    }

    public long getPostId() {
        return mPostId;
    }

}
