package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ContactUserDTO {

    @SerializedName("mobile")
    private int mMobile;

    @SerializedName("skype")
    private String mSkype;

    @SerializedName("email")
    private String mEmail;

    public ContactUserDTO() {
    }

    public ContactUserDTO(int mobile, String skype, String email) {
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

        ContactUserDTO that = (ContactUserDTO) o;

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
        return "ContactUserDTO{" +
                "mMobile=" + mMobile +
                ", mSkype='" + mSkype + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }
}
