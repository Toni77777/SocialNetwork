package by.grodno.toni7777.socialnetwork.base.event;

public class SearchGroupEvent {

    private final long mSearchGroupId;

    public SearchGroupEvent(long groupId) {
        mSearchGroupId = groupId;
    }

    public long getGroupId() {
        return mSearchGroupId;
    }
}
