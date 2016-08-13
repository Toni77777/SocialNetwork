package by.grodno.toni7777.socialnetwork.ui.model;

public class OwnerDVO {

    private String mName;
    private String mSurname;
    private String mAvatar;
    private String mFullName;

    public OwnerDVO() {
    }

    public OwnerDVO(String name, String surname, String avatar) {
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

    public String getFullName() {
        if (mFullName == null) {
            mFullName = mName + " " + mSurname;
        }
        return mFullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerDVO ownerDVO = (OwnerDVO) o;

        if (mName != null ? !mName.equals(ownerDVO.mName) : ownerDVO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(ownerDVO.mSurname) : ownerDVO.mSurname != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(ownerDVO.mAvatar) : ownerDVO.mAvatar != null)
            return false;
        return mFullName != null ? mFullName.equals(ownerDVO.mFullName) : ownerDVO.mFullName == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mFullName != null ? mFullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerDVO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mFullName='" + mFullName + '\'' +
                '}';
    }
}
