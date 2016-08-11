package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class NewPostDTO {

    @SerializedName("idTo")
    private long mToUserId;

    @SerializedName("fkImage")
    private Long mImageId;

    @SerializedName("text")
    private String mText;

    public NewPostDTO() {
    }

    public NewPostDTO(long toUserId, Long imageId, String text) {
        mToUserId = toUserId;
        mImageId = imageId;
        mText = text;
    }

    public long getToUserId() {
        return mToUserId;
    }

    public void setToUserId(long toUserId) {
        mToUserId = toUserId;
    }

    public Long getImageId() {
        return mImageId;
    }

    public void setImageId(Long imageId) {
        mImageId = imageId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
