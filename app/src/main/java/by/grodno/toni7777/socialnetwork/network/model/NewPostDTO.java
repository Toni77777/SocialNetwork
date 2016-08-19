package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class NewPostDTO {

    @SerializedName("idTo")
    private long mToUserId;

    @SerializedName("urlImage")
    private String mImageURL;

    @SerializedName("text")
    private String mText;

    public NewPostDTO() {
    }

    public NewPostDTO(long toUserId, String imageURL, String text) {
        mToUserId = toUserId;
        mImageURL = imageURL;
        mText = text;
    }

    public long getToUserId() {
        return mToUserId;
    }

    public void setToUserId(long toUserId) {
        mToUserId = toUserId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }
}
