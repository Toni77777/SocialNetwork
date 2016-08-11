package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

// Remove this class after response refactor
public class ProfileDTO {

    @SerializedName("entity")
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileDTO that = (ProfileDTO) o;

        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "user=" + user +
                '}';
    }
}
