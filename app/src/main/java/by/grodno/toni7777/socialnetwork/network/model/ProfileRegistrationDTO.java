package by.grodno.toni7777.socialnetwork.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public final class ProfileRegistrationDTO implements Parcelable {

    @SerializedName("name")
    private String mName;

    @SerializedName("lastName")
    private String mSurname;

    @SerializedName("sex")
    private int mSex;

    @SerializedName("bday")
    private String mDateBirth;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("email")
    private String mEmail;

    public ProfileRegistrationDTO() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        this.mSurname = surname;
    }

    public int getSex() {
        return mSex;
    }

    public void setSex(int sex) {
        this.mSex = sex;
    }

    public String getDateBirth() {
        return mDateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.mDateBirth = dateBirth;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileRegistrationDTO profile = (ProfileRegistrationDTO) o;

        if (mName != null ? !mName.equals(profile.mName) : profile.mName != null) return false;
        if (mSurname != null ? !mSurname.equals(profile.mSurname) : profile.mSurname != null)
            return false;
        if (mDateBirth != null ? !mDateBirth.equals(profile.mDateBirth) : profile.mDateBirth != null)
            return false;
        if (mLogin != null ? !mLogin.equals(profile.mLogin) : profile.mLogin != null) return false;
        if (mPassword != null ? !mPassword.equals(profile.mPassword) : profile.mPassword != null)
            return false;
        return mEmail != null ? mEmail.equals(profile.mEmail) : profile.mEmail == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
        result = 31 * result + (mDateBirth != null ? mDateBirth.hashCode() : 0);
        result = 31 * result + (mLogin != null ? mLogin.hashCode() : 0);
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileRegistrationDTO{" +
                "mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mSex='" + mSex + '\'' +
                ", mDateBirth='" + mDateBirth + '\'' +
                ", mLogin='" + mLogin + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mSurname);
        parcel.writeInt(mSex);
        parcel.writeString(mDateBirth);
        parcel.writeString(mLogin);
        parcel.writeString(mPassword);
        parcel.writeString(mEmail);
    }

    protected ProfileRegistrationDTO(Parcel in) {
        mName = in.readString();
        mSurname = in.readString();
        mSex = in.readInt();
        mDateBirth = in.readString();
        mLogin = in.readString();
        mPassword = in.readString();
        mEmail = in.readString();

    }

    public static final Creator<ProfileRegistrationDTO> CREATOR = new Creator<ProfileRegistrationDTO>() {
        @Override
        public ProfileRegistrationDTO createFromParcel(Parcel in) {
            return new ProfileRegistrationDTO(in);
        }

        @Override
        public ProfileRegistrationDTO[] newArray(int size) {
            return new ProfileRegistrationDTO[size];
        }
    };
}
