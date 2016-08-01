package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class OwnerDTO {

    private String name;
    @SerializedName("lastName")
    private String surname;
    private String avatar;

    public OwnerDTO() {
    }

    public OwnerDTO(String name, String surname, String avatar) {
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
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

        OwnerDTO ownerDTO = (OwnerDTO) o;

        if (name != null ? !name.equals(ownerDTO.name) : ownerDTO.name != null) return false;
        if (surname != null ? !surname.equals(ownerDTO.surname) : ownerDTO.surname != null)
            return false;
        return avatar != null ? avatar.equals(ownerDTO.avatar) : ownerDTO.avatar == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
