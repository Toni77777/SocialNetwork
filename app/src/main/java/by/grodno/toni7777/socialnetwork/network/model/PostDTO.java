package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PostDTO {

    @SerializedName("owner")
    private OwnerDTO mOwner;

    @SerializedName("image")
    private String mImage;

    @SerializedName("text")
    private String mText;

    @SerializedName("like")
    private int mLike;

    @SerializedName("dislike")
    private int mDislike;

    public PostDTO() {
    }

    public PostDTO(OwnerDTO owner, String image, String text, int like, int dislike) {
        mOwner = owner;
        mImage = image;
        mText = text;
        mLike = like;
        mDislike = dislike;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDTO postDTO = (PostDTO) o;

        if (mLike != postDTO.mLike) return false;
        if (mDislike != postDTO.mDislike) return false;
        if (mOwner != null ? !mOwner.equals(postDTO.mOwner) : postDTO.mOwner != null) return false;
        if (mImage != null ? !mImage.equals(postDTO.mImage) : postDTO.mImage != null) return false;
        return mText != null ? mText.equals(postDTO.mText) : postDTO.mText == null;

    }

    @Override
    public int hashCode() {
        int result = mOwner != null ? mOwner.hashCode() : 0;
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        result = 31 * result + mLike;
        result = 31 * result + mDislike;
        return result;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "mOwner=" + mOwner +
                ", mImage='" + mImage + '\'' +
                ", mText='" + mText + '\'' +
                ", mLike=" + mLike +
                ", mDislike=" + mDislike +
                '}';
    }
}
