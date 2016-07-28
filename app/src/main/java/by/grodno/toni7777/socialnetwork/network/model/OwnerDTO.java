package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class OwnerDTO {

    private String name;
    @SerializedName("lastName")
    private String lastname;
    private String avatar;

    public OwnerDTO() {
    }

    public OwnerDTO(String name, String lastname, String avatar) {
        this.name = name;
        this.lastname = lastname;
        this.avatar = avatar;
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
        if (lastname != null ? !lastname.equals(ownerDTO.lastname) : ownerDTO.lastname != null)
            return false;
        return avatar != null ? avatar.equals(ownerDTO.avatar) : ownerDTO.avatar == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
