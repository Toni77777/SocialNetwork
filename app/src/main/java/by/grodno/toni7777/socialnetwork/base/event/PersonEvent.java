package by.grodno.toni7777.socialnetwork.base.event;

public class PersonEvent {

    private final long mPersonId;

    public PersonEvent(long friendId) {
        mPersonId = friendId;
    }

    public long getFriendId() {
        return mPersonId;
    }
}
