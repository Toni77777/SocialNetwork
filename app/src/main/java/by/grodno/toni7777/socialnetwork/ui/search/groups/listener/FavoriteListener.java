package by.grodno.toni7777.socialnetwork.ui.search.groups.listener;

public interface FavoriteListener {

    void addGroupToFavoriteCompleted(Long groupId);

    void addGroupToFavoriteError(Throwable e);

}
