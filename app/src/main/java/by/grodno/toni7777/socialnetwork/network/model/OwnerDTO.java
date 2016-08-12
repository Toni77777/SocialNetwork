package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class OwnerDTO {

    @SerializedName("name")
    private String mName;

    @SerializedName("lastName")
    private String mSurname;

    @SerializedName("avatar")
    private String mAvatar;

    private String mFullName;

    public OwnerDTO() {
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
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

        OwnerDTO ownerDTO = (OwnerDTO) o;

        if (mName != null ? !mName.equals(ownerDTO.mName) : ownerDTO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(ownerDTO.mSurname) : ownerDTO.mSurname != null)
            return false;
        return mAvatar != null ? mAvatar.equals(ownerDTO.mAvatar) : ownerDTO.mAvatar == null;

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
        return "OwnerDTO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", fullName='" + mFullName + '\'' +
                '}';
    }
}
