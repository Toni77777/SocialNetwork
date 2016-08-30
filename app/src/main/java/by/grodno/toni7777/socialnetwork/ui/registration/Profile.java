package by.grodno.toni7777.socialnetwork.ui.registration;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public final class Profile implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("lastName")
    private String surname;

    @SerializedName("sex")
    private String sex;

    @SerializedName("bday")
    private String dateBirth;

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (surname != null ? !surname.equals(profile.surname) : profile.surname != null)
            return false;
        if (sex != null ? !sex.equals(profile.sex) : profile.sex != null) return false;
        if (dateBirth != null ? !dateBirth.equals(profile.dateBirth) : profile.dateBirth != null)
            return false;
        if (login != null ? !login.equals(profile.login) : profile.login != null) return false;
        if (password != null ? !password.equals(profile.password) : profile.password != null)
            return false;
        return email != null ? email.equals(profile.email) : profile.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (dateBirth != null ? dateBirth.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(sex);
        parcel.writeString(dateBirth);
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeString(email);
    }

    protected Profile(Parcel in) {
        name = in.readString();
        surname = in.readString();
        sex = in.readString();
        dateBirth = in.readString();
        login = in.readString();
        password = in.readString();
        email = in.readString();

    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
