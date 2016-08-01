package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallDTO {

    @SerializedName("entity")
    private List<PostDTO> posts;

    public WallDTO() {
    }

    public WallDTO(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallDTO wallDTO = (WallDTO) o;

        return posts != null ? posts.equals(wallDTO.posts) : wallDTO.posts == null;

    }

    @Override
    public int hashCode() {
        return posts != null ? posts.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WallDTO{" +
                "posts=" + posts +
                '}';
    }
}
