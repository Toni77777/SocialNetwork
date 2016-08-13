package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProfileDSO extends RealmObject {

    @PrimaryKey
    private long mId;
    private String mName;
    private String mSurname;
    private int mSex;
    private String mBirthday;
    private String mAvatar;
    private String mCity;
    private String mAbout;
    private ContactProfileDSO mContact;

    public ProfileDSO() {
    }

    public ProfileDSO(long id, String name, String surname, int sex, String birthday, String avatar, String city, String about, ContactProfileDSO contact) {
        mId = id;
        mName = name;
        mSurname = surname;
        mSex = sex;
        mBirthday = birthday;
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

    public int getSex() {
        return mSex;
    }

    public void setSex(int sex) {
        mSex = sex;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
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

    public ContactProfileDSO getContact() {
        return mContact;
    }

    public void setContact(ContactProfileDSO contact) {
        mContact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileDSO that = (ProfileDSO) o;

        if (mId != that.mId) return false;
        if (mSex != that.mSex) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(that.mSurname) : that.mSurname != null)
            return false;
        if (mBirthday != null ? !mBirthday.equals(that.mBirthday) : that.mBirthday != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(that.mAvatar) : that.mAvatar != null) return false;
        if (mCity != null ? !mCity.equals(that.mCity) : that.mCity != null) return false;
        if (mAbout != null ? !mAbout.equals(that.mAbout) : that.mAbout != null) return false;
        return mContact != null ? mContact.equals(that.mContact) : that.mContact == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + mSex;
        result = 31 * result + (mBirthday != null ? mBirthday.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mCity != null ? mCity.hashCode() : 0);
        result = 31 * result + (mAbout != null ? mAbout.hashCode() : 0);
        result = 31 * result + (mContact != null ? mContact.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileDSO{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mSex=" + mSex +
                ", mBirthday='" + mBirthday + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mAbout='" + mAbout + '\'' +
                ", mContact=" + mContact +
                '}';
    }
}
