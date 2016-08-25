package by.grodno.toni7777.socialnetwork.ui.search.persons.listener;

public interface FriendListener {

    void addNewFriendCompleted(Long userId);

    void addNewFriendError(Throwable e);
}
