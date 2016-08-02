package by.grodno.toni7777.socialnetwork.network.model;

public class FriendDTO {

    private String name;
    private String surname;
    private long id;
    private String avatar;

    public FriendDTO() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendDTO friendDTO = (FriendDTO) o;

        if (id != friendDTO.id) return false;
        if (name != null ? !name.equals(friendDTO.name) : friendDTO.name != null) return false;
        if (surname != null ? !surname.equals(friendDTO.surname) : friendDTO.surname != null)
            return false;
        return avatar != null ? avatar.equals(friendDTO.avatar) : friendDTO.avatar == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
