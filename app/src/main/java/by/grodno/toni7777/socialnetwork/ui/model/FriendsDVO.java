package by.grodno.toni7777.socialnetwork.ui.model;

import java.util.List;

public class FriendsDVO {

    private List<FriendDVO> mFriends;
    private int mCount;

    public FriendsDVO() {
    }

    public FriendsDVO(List<FriendDVO> friends, int count) {
        mFriends = friends;
        mCount = count;
    }

    public List<FriendDVO> getFriends() {
        return mFriends;
    }

    public void setFriends(List<FriendDVO> friends) {
        mFriends = friends;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDVO that = (FriendsDVO) o;

        if (mCount != that.mCount) return false;
        return mFriends != null ? mFriends.equals(that.mFriends) : that.mFriends == null;

    }

    @Override
    public int hashCode() {
        int result = mFriends != null ? mFriends.hashCode() : 0;
        result = 31 * result + mCount;
        return result;
    }

    @Override
    public String toString() {
        return "FriendsDVO{" +
                "mFriends=" + mFriends +
                ", mCount=" + mCount +
                '}';
    }
}
