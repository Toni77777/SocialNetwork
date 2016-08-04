package by.grodno.toni7777.socialnetwork.network.model;

import java.util.List;

public class FriendsDTO {

    private List<FriendDTO> friends;
    private long numbers;

    public FriendsDTO() {
    }

    public FriendsDTO(List<FriendDTO> friends, long numbers) {
        this.friends = friends;
        this.numbers = numbers;
    }

    public List<FriendDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendDTO> friends) {
        this.friends = friends;
    }

    public long getNumbers() {
        return numbers;
    }

    public void setNumbers(long numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDTO that = (FriendsDTO) o;

        if (numbers != that.numbers) return false;
        return friends != null ? friends.equals(that.friends) : that.friends == null;

    }

    @Override
    public int hashCode() {
        int result = friends != null ? friends.hashCode() : 0;
        result = 31 * result + (int) (numbers ^ (numbers >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "FriendsDTO{" +
                "friends=" + friends +
                ", numbers=" + numbers +
                '}';
    }
}
