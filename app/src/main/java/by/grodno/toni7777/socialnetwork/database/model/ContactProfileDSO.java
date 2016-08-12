package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;

public class ContactProfileDSO extends RealmObject {

    private int mMobile;
    private String mSkype;
    private String mEmail;

    public ContactProfileDSO() {
    }

    public ContactProfileDSO(int mobile, String skype, String email) {
        mMobile = mobile;
        mSkype = skype;
        mEmail = email;
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

        ContactProfileDSO that = (ContactProfileDSO) o;

        if (mMobile != that.mMobile) return false;
        if (mSkype != null ? !mSkype.equals(that.mSkype) : that.mSkype != null) return false;
        return mEmail != null ? mEmail.equals(that.mEmail) : that.mEmail == null;

    }

    @Override
    public int hashCode() {
        int result = mMobile;
        result = 31 * result + (mSkype != null ? mSkype.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactProfileDSO{" +
                "mMobile=" + mMobile +
                ", mSkype='" + mSkype + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }
}
