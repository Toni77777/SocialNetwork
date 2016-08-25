package by.grodno.toni7777.socialnetwork.ui.model;

public class PersonDVO {

    private long mId;
    private String mName;
    private String mSurname;
    private String mAvatar;
    private String mFullName;

    public PersonDVO() {
    }

    public PersonDVO(long id, String name, String surname, String avatar) {
        mId = id;
        mName = name;
        mSurname = surname;
        mAvatar = avatar;
    }

    public String getFullName() {
        if (mFullName == null) {
            mFullName = mName + " " + mSurname;
        }
        return mFullName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
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

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDVO personDVO = (PersonDVO) o;

        if (mId != personDVO.mId) return false;
        if (mName != null ? !mName.equals(personDVO.mName) : personDVO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(personDVO.mSurname) : personDVO.mSurname != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(personDVO.mAvatar) : personDVO.mAvatar != null)
            return false;
        return mFullName != null ? mFullName.equals(personDVO.mFullName) : personDVO.mFullName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mFullName != null ? mFullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDVO{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mFullName='" + mFullName + '\'' +
                '}';
    }
}
