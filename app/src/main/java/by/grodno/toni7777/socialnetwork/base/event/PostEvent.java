package by.grodno.toni7777.socialnetwork.base.event;

public class PostEvent {

    private long mPostId;

    public PostEvent(long postId) {
        mPostId = postId;
    }

    public long getPostId() {
        return mPostId;
    }

}
