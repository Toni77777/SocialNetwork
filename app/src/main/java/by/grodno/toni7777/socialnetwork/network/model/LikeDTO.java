package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class LikeDTO {

    @SerializedName("isLike")
    private Integer mLike;

    public LikeDTO() {
    }

    public LikeDTO(Integer like) {
        mLike = like;
    }

    public Integer getLike() {
        return mLike;
    }

    public void setLike(Integer like) {
        mLike = like;
    }

}
