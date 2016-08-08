package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallDTO {

    @SerializedName("entity")
    private List<PostDTO> mPosts;

    @SerializedName("numbers")
    private long mNumbers;

    public WallDTO() {
    }

    public WallDTO(List<PostDTO> posts, long numbers) {
        mPosts = posts;
        mNumbers = numbers;
    }

    public List<PostDTO> getPosts() {
        return mPosts;
    }

    public void setPosts(List<PostDTO> posts) {
        mPosts = posts;
    }

    public long getNumbers() {
        return mNumbers;
    }

    public void setNumbers(long numbers) {
        mNumbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallDTO wallDTO = (WallDTO) o;

        if (mNumbers != wallDTO.mNumbers) return false;
        return mPosts != null ? mPosts.equals(wallDTO.mPosts) : wallDTO.mPosts == null;

    }

    @Override
    public int hashCode() {
        int result = mPosts != null ? mPosts.hashCode() : 0;
        result = 31 * result + (int) (mNumbers ^ (mNumbers >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WallDTO{" +
                "mPosts=" + mPosts +
                ", mNumbers=" + mNumbers +
                '}';
    }
}
