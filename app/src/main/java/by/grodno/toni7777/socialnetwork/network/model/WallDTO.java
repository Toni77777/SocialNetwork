package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallDTO {

    @SerializedName("entity")
    private List<PostDTO> posts;
    private String status;

    public WallDTO() {
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallDTO wallDTO = (WallDTO) o;

        if (posts != null ? !posts.equals(wallDTO.posts) : wallDTO.posts != null) return false;
        return status != null ? status.equals(wallDTO.status) : wallDTO.status == null;

    }

    @Override
    public int hashCode() {
        int result = posts != null ? posts.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WallDTO{" +
                "posts=" + posts +
                ", status='" + status + '\'' +
                '}';
    }
}
