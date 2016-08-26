package by.grodno.toni7777.socialnetwork.database.model;

import by.grodno.toni7777.socialnetwork.database.Keys;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GroupsDSO extends RealmObject {

    @PrimaryKey
    private int key = Keys.GROUPS_KEY;
    private RealmList<GroupDSO> mGroupsDSO;

    public GroupsDSO() {
    }

    public GroupsDSO(RealmList<GroupDSO> groupsDSO) {
        mGroupsDSO = groupsDSO;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public RealmList<GroupDSO> getGroupsDSO() {
        return mGroupsDSO;
    }

    public void setGroupsDSO(RealmList<GroupDSO> groupsDSO) {
        mGroupsDSO = groupsDSO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsDSO groupsDSO = (GroupsDSO) o;

        if (key != groupsDSO.key) return false;
        return mGroupsDSO != null ? mGroupsDSO.equals(groupsDSO.mGroupsDSO) : groupsDSO.mGroupsDSO == null;

    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + (mGroupsDSO != null ? mGroupsDSO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupsDSO{" +
                "key=" + key +
                ", mGroupsDSO=" + mGroupsDSO +
                '}';
    }
}
