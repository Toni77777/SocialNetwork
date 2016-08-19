package by.grodno.toni7777.socialnetwork.base.event;

public class GroupEvent {

    private final long mGroupId;

    public GroupEvent(long groupId) {
        mGroupId = groupId;
    }

    public long getGroupId() {
        return mGroupId;
    }
}
