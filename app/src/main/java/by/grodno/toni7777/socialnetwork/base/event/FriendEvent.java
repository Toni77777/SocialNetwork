package by.grodno.toni7777.socialnetwork.base.event;

public class FriendEvent {

    private final long mFriendId;

    public FriendEvent(long friendId) {
        mFriendId = friendId;
    }

    public long getFriendId() {
        return mFriendId;
    }
}
