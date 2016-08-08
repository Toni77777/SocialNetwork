package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class FriendDTO {

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("id")
    private long mId;

    @SerializedName("avatar")
    private String mAvatar;

    public FriendDTO() {
    }

    public FriendDTO(String name, String surname, long id, String avatar) {
        mName = name;
        mSurname = surname;
        mId = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendDTO friendDTO = (FriendDTO) o;

        if (mId != friendDTO.mId) return false;
        if (mName != null ? !mName.equals(friendDTO.mName) : friendDTO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(friendDTO.mSurname) : friendDTO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(friendDTO.mAvatar) : friendDTO.mAvatar == null;

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
        return "FriendDTO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mId=" + mId +
                ", mAvatar='" + mAvatar + '\'' +
                '}';
    }
}
