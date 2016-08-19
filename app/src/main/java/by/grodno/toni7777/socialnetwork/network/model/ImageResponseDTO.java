package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ImageResponseDTO {

    @SerializedName("entity")
    private String mImageURL;

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageId(String imageURL) {
        mImageURL = imageURL;
    }
}
