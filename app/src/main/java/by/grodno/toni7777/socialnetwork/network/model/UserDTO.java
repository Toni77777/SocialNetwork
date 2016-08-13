package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("lastName")
    private String mSurname;

    @SerializedName("birthday")
    private String mBirthday;

    @SerializedName("sex")
    private int mSex;

    @SerializedName("avatar")
    private String mAvatar;

    @SerializedName("city")
    private String mCity;

    @SerializedName("about")
    private String mAbout;

    @SerializedName("contactUser")
    private ContactUserDTO mContact;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String surname, String birthday, int sex, String avatar, String city, String about, ContactUserDTO contact) {
        mId = id;
        mName = name;
        mSurname = surname;
        mBirthday = birthday;
        mSex = sex;
        mAvatar = avatar;
        mCity = city;
        mAbout = about;
        mContact = contact;
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

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }

    public int getSex() {
        return mSex;
    }

    public void setSex(int sex) {
        mSex = sex;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String about) {
        mAbout = about;
    }

    public ContactUserDTO getContact() {
        return mContact;
    }

    public void setContact(ContactUserDTO contact) {
        mContact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (mId != userDTO.mId) return false;
        if (mSex != userDTO.mSex) return false;
        if (mName != null ? !mName.equals(userDTO.mName) : userDTO.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(userDTO.mSurname) : userDTO.mSurname != null)
            return false;
        if (mBirthday != null ? !mBirthday.equals(userDTO.mBirthday) : userDTO.mBirthday != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(userDTO.mAvatar) : userDTO.mAvatar != null)
            return false;
        if (mCity != null ? !mCity.equals(userDTO.mCity) : userDTO.mCity != null) return false;
        if (mAbout != null ? !mAbout.equals(userDTO.mAbout) : userDTO.mAbout != null) return false;
        return mContact != null ? mContact.equals(userDTO.mContact) : userDTO.mContact == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mBirthday != null ? mBirthday.hashCode() : 0);
        result = 31 * result + mSex;
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mCity != null ? mCity.hashCode() : 0);
        result = 31 * result + (mAbout != null ? mAbout.hashCode() : 0);
        result = 31 * result + (mContact != null ? mContact.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mBirthday='" + mBirthday + '\'' +
                ", mSex=" + mSex +
                ", mAvatar='" + mAvatar + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mAbout='" + mAbout + '\'' +
                ", mContact=" + mContact +
                '}';
    }
}
