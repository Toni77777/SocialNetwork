package by.grodno.toni7777.socialnetwork.ui.model;

public class ProfileDVO {

    private long mId;
    private String mName;
    private String mSurname;
    private String mBirthday;
    private String mAvatar;
    private String mCity;
    private String mAbout;
    private int mMobile;
    private String mSkype;
    private String mEmail;
    private String mFullName;

    public ProfileDVO() {
    }

    public ProfileDVO(long id, String name, String surname, String birthday, String avatar, String city, String about, int mobile, String skype, String email) {
        mId = id;
        mName = name;
        mSurname = surname;
        mBirthday = birthday;
        mAvatar = avatar;
        mCity = city;
        mAbout = about;
        mMobile = mobile;
        mSkype = skype;
        mEmail = email;
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

    public int getMobile() {
        return mMobile;
    }

    public void setMobile(int mobile) {
        mMobile = mobile;
    }

    public String getSkype() {
        return mSkype;
    }

    public void setSkype(String skype) {
        mSkype = skype;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileDVO that = (ProfileDVO) o;

        if (mId != that.mId) return false;
        if (mMobile != that.mMobile) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(that.mSurname) : that.mSurname != null)
            return false;
        if (mBirthday != null ? !mBirthday.equals(that.mBirthday) : that.mBirthday != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(that.mAvatar) : that.mAvatar != null) return false;
        if (mCity != null ? !mCity.equals(that.mCity) : that.mCity != null) return false;
        if (mAbout != null ? !mAbout.equals(that.mAbout) : that.mAbout != null) return false;
        if (mSkype != null ? !mSkype.equals(that.mSkype) : that.mSkype != null) return false;
        if (mEmail != null ? !mEmail.equals(that.mEmail) : that.mEmail != null) return false;
        return mFullName != null ? mFullName.equals(that.mFullName) : that.mFullName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mBirthday != null ? mBirthday.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mCity != null ? mCity.hashCode() : 0);
        result = 31 * result + (mAbout != null ? mAbout.hashCode() : 0);
        result = 31 * result + mMobile;
        result = 31 * result + (mSkype != null ? mSkype.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mFullName != null ? mFullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileDVO{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mBirthday='" + mBirthday + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mAbout='" + mAbout + '\'' +
                ", mMobile=" + mMobile +
                ", mSkype='" + mSkype + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mFullName='" + mFullName + '\'' +
                '}';
    }
}
