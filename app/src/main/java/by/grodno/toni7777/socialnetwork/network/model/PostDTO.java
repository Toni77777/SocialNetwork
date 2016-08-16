package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PostDTO {

    @SerializedName("id")
    private long mPostId;

    @SerializedName("owner")
    private OwnerDTO mOwner;

    @SerializedName("image")
    private String mImage;

    @SerializedName("date")
    private String mDate;

    @SerializedName("text")
    private String mText;

    @SerializedName("like")
    private int mLike;

    @SerializedName("dislike")
    private int mDislike;

    public PostDTO() {
    }

    public PostDTO(long postId, OwnerDTO owner, String image, String date, String text, int like, int dislike) {
        mPostId = postId;
        mOwner = owner;
        mImage = image;
        mDate = date;
        mText = text;
        mLike = like;
        mDislike = dislike;
    }

    public long getPostId() {
        return mPostId;
    }

    public void setPostId(long postId) {
        mPostId = postId;
    }

    public OwnerDTO getOwner() {
        return mOwner;
    }

    public void setOwner(OwnerDTO owner) {
        mOwner = owner;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getLike() {
        return mLike;
    }

    public void setLike(int like) {
        mLike = like;
    }

    public int getDislike() {
        return mDislike;
    }

    public void setDislike(int dislike) {
        mDislike = dislike;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "mPostId=" + mPostId +
                ", mOwner=" + mOwner +
                ", mImage='" + mImage + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mText='" + mText + '\'' +
                ", mLike=" + mLike +
                ", mDislike=" + mDislike +
                '}';
    }
}
