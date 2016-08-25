package by.grodno.toni7777.socialnetwork.database.model;

import by.grodno.toni7777.socialnetwork.database.Keys;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FriendsDSO extends RealmObject {

    @PrimaryKey
    private int key = Keys.FRIENDS_KEY;
    private RealmList<FriendDSO> mFriendsDSO;

    public FriendsDSO() {
    }

    public FriendsDSO(RealmList<FriendDSO> friendsDSO) {
        mFriendsDSO = friendsDSO;
    }

    public int getKey() {
        return key;
    }

    public RealmList<FriendDSO> getFriendsDSO() {
        return mFriendsDSO;
    }

    public void setFriendsDSO(RealmList<FriendDSO> friendsDSO) {
        mFriendsDSO = friendsDSO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDSO that = (FriendsDSO) o;

        if (key != that.key) return false;
        return mFriendsDSO != null ? mFriendsDSO.equals(that.mFriendsDSO) : that.mFriendsDSO == null;

    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + (mFriendsDSO != null ? mFriendsDSO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendsDSO{" +
                "key=" + key +
                ", mFriendsDSO=" + mFriendsDSO +
                '}';
    }
}
