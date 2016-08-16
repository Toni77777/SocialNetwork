package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ImageResponseDTO {

    @SerializedName("entity")
    private Long mImageId;

    public Long getImageId() {
        return mImageId;
    }

    public void setImageId(Long imageId) {
        mImageId = imageId;
    }
}
