package by.grodno.toni7777.socialnetwork.ui.group.listener;

public interface GroupPagination {

    void loadDataWithOffset(long groupId, boolean forceRefresh, int offset);
}
