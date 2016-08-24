package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FriendDSO extends RealmObject {

    @PrimaryKey
    private String mKey;
    private String mName;
    private String mSurname;
    private long mId;
    private String mAvatar;
    private boolean mOnline;

    public FriendDSO() {
    }

    public FriendDSO(String key, String name, String surname, long id, String avatar, boolean online) {
        mKey = key;
        mName = name;
        mSurname = surname;
        mId = id;
        mAvatar = avatar;
        mOnline = online;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public void setOnline(boolean online) {
        mOnline = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendDSO friendDSO = (FriendDSO) o;

        if (mId != friendDSO.mId) return false;
        if (mOnline != friendDSO.mOnline) return false;
        if (mKey != null ? !mKey.equals(friendDSO.mKey) : friendDSO.mKey != null) return false;
        if (mName != null ? !mName.equals(friendDSO.mName) : friendDSO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(friendDSO.mSurname) : friendDSO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(friendDSO.mAvatar) : friendDSO.mAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = mKey != null ? mKey.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mOnline ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendDSO{" +
                "mKey='" + mKey + '\'' +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mId=" + mId +
                ", mAvatar='" + mAvatar + '\'' +
                ", mOnline=" + mOnline +
                '}';
    }
}
