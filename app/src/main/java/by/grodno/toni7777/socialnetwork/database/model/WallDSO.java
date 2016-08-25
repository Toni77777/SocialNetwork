package by.grodno.toni7777.socialnetwork.database.model;

import by.grodno.toni7777.socialnetwork.database.Keys;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WallDSO extends RealmObject {

    @PrimaryKey
    private int key = Keys.WALL_KEY;
    private RealmList<PostDSO> mPostDSO;

    public WallDSO() {
    }

    public WallDSO(RealmList<PostDSO> postDSO) {
        mPostDSO = postDSO;
    }

    public RealmList<PostDSO> getPostDSO() {
        return mPostDSO;
    }

    public void setPostDSO(RealmList<PostDSO> postDSO) {
        mPostDSO = postDSO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallDSO wallDSO = (WallDSO) o;

        return mPostDSO != null ? mPostDSO.equals(wallDSO.mPostDSO) : wallDSO.mPostDSO == null;

    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + (mPostDSO != null ? mPostDSO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WallDSO{" +
                "key=" + key +
                ", mPostDSO=" + mPostDSO +
                '}';
    }
}
