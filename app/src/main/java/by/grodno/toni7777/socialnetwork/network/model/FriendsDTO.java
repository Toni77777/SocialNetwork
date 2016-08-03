package by.grodno.toni7777.socialnetwork.network.model;

import java.util.List;

public class FriendsDTO {

    private List<FriendDTO> friends;

    public FriendsDTO(List<FriendDTO> friends) {
        this.friends = friends;
    }

    public List<FriendDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendDTO> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDTO that = (FriendsDTO) o;

        return friends != null ? friends.equals(that.friends) : that.friends == null;

    }

    @Override
    public int hashCode() {
        return friends != null ? friends.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FriendsDTO{" +
                "friends=" + friends +
                '}';
    }
}
