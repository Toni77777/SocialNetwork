package by.grodno.toni7777.socialnetwork.ui.model;

public class FriendDVO {

    private String mName;
    private String mSurname;
    private long mId;
    private String mAvatar;
    private String mFullName;
    private boolean mOnline;

    public FriendDVO() {
    }

    public FriendDVO(String name, String surname, long id, String avatar, boolean online) {
        mName = name;
        mSurname = surname;
        mId = id;
        mAvatar = avatar;
        mOnline = online;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public void setOnline(boolean online) {
        mOnline = online;
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

        FriendDVO friendDVO = (FriendDVO) o;

        if (mId != friendDVO.mId) return false;
        if (mName != null ? !mName.equals(friendDVO.mName) : friendDVO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(friendDVO.mSurname) : friendDVO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(friendDVO.mAvatar) : friendDVO.mAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendDVO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mId=" + mId +
                ", mAvatar='" + mAvatar + '\'' +
                '}';
    }
}
