package by.grodno.toni7777.socialnetwork.model;

import java.util.Date;
import java.util.List;

public class User {

    private long userID;
    private String name;
    private String lastname;
    private Date birthDate;
    private Sex sex;
    private String country;
    private String city;
    private ContactInfo contactInfo;
    private String avatarUrl;
    private List<Long> friends;
    private Wall wall;
    private List<Long> groupsID;

    public User() {
    }

    public User(long userID, String name, String lastname, Date birthDate, Sex sex, String country, String city, ContactInfo contactInfo, String avatarUrl, List<Long> friends, Wall wall, List<Long> groupsID) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.contactInfo = contactInfo;
        this.avatarUrl = avatarUrl;
        this.friends = friends;
        this.wall = wall;
        this.groupsID = groupsID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Long> getFriends() {
        return friends;
    }

    public void setFriends(List<Long> friends) {
        this.friends = friends;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public List<Long> getGroupsID() {
        return groupsID;
    }

    public void setGroupsID(List<Long> groupsID) {
        this.groupsID = groupsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userID != user.userID) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null)
            return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null)
            return false;
        if (sex != user.sex) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (contactInfo != null ? !contactInfo.equals(user.contactInfo) : user.contactInfo != null)
            return false;
        if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null)
            return false;
        if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
        if (wall != null ? !wall.equals(user.wall) : user.wall != null) return false;
        return groupsID != null ? groupsID.equals(user.groupsID) : user.groupsID == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (userID ^ (userID >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (contactInfo != null ? contactInfo.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + (wall != null ? wall.hashCode() : 0);
        result = 31 * result + (groupsID != null ? groupsID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", contactInfo=" + contactInfo +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", friends=" + friends +
                ", wall=" + wall +
                ", groupsID=" + groupsID +
                '}';
    }
}
