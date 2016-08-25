package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PersonDTO {

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("lastName")
    private String mSurname;

    @SerializedName("avatar")
    private String mAvatar;

    @SerializedName("isFriend")
    private int mFriend;

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

    public int getFriend() {
        return mFriend;
    }

    public void setFriend(int friend) {
        mFriend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDTO personDTO = (PersonDTO) o;

        if (mId != personDTO.mId) return false;
        if (mFriend != personDTO.mFriend) return false;
        if (mName != null ? !mName.equals(personDTO.mName) : personDTO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(personDTO.mSurname) : personDTO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(personDTO.mAvatar) : personDTO.mAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + mFriend;
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mFriend=" + mFriend +
                '}';
    }
}
