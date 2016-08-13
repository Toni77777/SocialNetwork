package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;

public class OwnerDSO extends RealmObject {

    private String mName;
    private String mSurname;
    private String mAvatar;

    public OwnerDSO() {
    }

    public OwnerDSO(String name, String surname, String avatar) {
        mName = name;
        mSurname = surname;
        mAvatar = avatar;
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

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerDSO ownerDSO = (OwnerDSO) o;

        if (mName != null ? !mName.equals(ownerDSO.mName) : ownerDSO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(ownerDSO.mSurname) : ownerDSO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(ownerDSO.mAvatar) : ownerDSO.mAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerDSO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                '}';
    }
}
