package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class LikeResponseDTO {

    @SerializedName("entity")
    private Integer mIsLike;

    public Integer getIsLike() {
        return mIsLike;
    }

    public void setIsLike(Integer isLike) {
        mIsLike = isLike;
    }

    @Override
    public String toString() {
        return "LikeResponseDTO{" +
                "mIsLike=" + mIsLike +
                '}';
    }
}
