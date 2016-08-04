package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallDTO {

    @SerializedName("entity")
    private List<PostDTO> posts;
    private long numbers;

    public WallDTO() {
    }

    public WallDTO(List<PostDTO> posts, long numbers) {
        this.posts = posts;
        this.numbers = numbers;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public long getNumbers() {
        return numbers;
    }

    public void setNumbers(long numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallDTO wallDTO = (WallDTO) o;

        if (numbers != wallDTO.numbers) return false;
        return posts != null ? posts.equals(wallDTO.posts) : wallDTO.posts == null;

    }

    @Override
    public int hashCode() {
        int result = posts != null ? posts.hashCode() : 0;
        result = 31 * result + (int) (numbers ^ (numbers >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WallDTO{" +
                "posts=" + posts +
                ", numbers=" + numbers +
                '}';
    }
}
