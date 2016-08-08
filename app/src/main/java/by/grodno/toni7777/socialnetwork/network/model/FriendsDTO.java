package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsDTO {

    @SerializedName("friends")
    private List<FriendDTO> mFriends;

    @SerializedName("numbers")
    private long mNumbers;

    public FriendsDTO() {
    }

    public FriendsDTO(List<FriendDTO> friends, long numbers) {
        mFriends = friends;
        mNumbers = numbers;
    }

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    public void setFriends(List<FriendDTO> friends) {
        mFriends = friends;
    }

    public long getNumbers() {
        return mNumbers;
    }

    public void setNumbers(long numbers) {
        mNumbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDTO that = (FriendsDTO) o;

        if (mNumbers != that.mNumbers) return false;
        return mFriends != null ? mFriends.equals(that.mFriends) : that.mFriends == null;

    }

    @Override
    public int hashCode() {
        int result = mFriends != null ? mFriends.hashCode() : 0;
        result = 31 * result + (int) (mNumbers ^ (mNumbers >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "FriendsDTO{" +
                "mFriends=" + mFriends +
                ", mNumbers=" + mNumbers +
                '}';
    }
}
