package by.grodno.toni7777.socialnetwork.registration;

import android.os.Parcel;
import android.os.Parcelable;

public final class Profile implements Parcelable {

    private String name;
    private String surname;
    private String sex;
    private String dateBirth;
    private String login;
    private String password;
    private String email;
    private String skype;
    private String telephone;
    private String city;

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

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (skype != null ? !skype.equals(profile.skype) : profile.skype != null) return false;
        if (telephone != null ? !telephone.equals(profile.telephone) : profile.telephone != null)
            return false;
        return city != null ? city.equals(profile.city) : profile.city == null;

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
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
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
                ", skype='" + skype + '\'' +
                ", telephone='" + telephone + '\'' +
                ", city='" + city + '\'' +
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
        parcel.writeString(skype);
        parcel.writeString(telephone);
        parcel.writeString(city);
    }

    protected Profile(Parcel in) {
        name = in.readString();
        surname = in.readString();
        sex = in.readString();
        dateBirth = in.readString();
        login = in.readString();
        password = in.readString();
        email = in.readString();
        skype = in.readString();
        telephone = in.readString();
        city = in.readString();
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
