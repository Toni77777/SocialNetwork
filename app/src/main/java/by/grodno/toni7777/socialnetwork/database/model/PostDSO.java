package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostDSO extends RealmObject {

    @PrimaryKey
    private String mKey;
    private long mPostId;
    private OwnerDSO mOwner;
    private String mImage;
    private String mDate;
    private String mText;
    private int mLike;
    private int mDislike;
    private Integer mIsLike;

    public PostDSO() {
    }

    public PostDSO(String key, long postId, OwnerDSO owner, String image, String date, String text, int like,
                   int dislike, Integer isLike) {
        mKey = key;
        mPostId = postId;
        mOwner = owner;
        mImage = image;
        mDate = date;
        mText = text;
        mLike = like;
        mDislike = dislike;
        mIsLike = isLike;
    }

    public long getPostId() {
        return mPostId;
    }

    public void setPostId(long postId) {
        mPostId = postId;
    }

    public OwnerDSO getOwner() {
        return mOwner;
    }

    public void setOwner(OwnerDSO owner) {
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

    public Integer getIsLike() {
        return mIsLike;
    }

    public void setIsLike(Integer isLike) {
        mIsLike = isLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDSO postDSO = (PostDSO) o;

        if (mPostId != postDSO.mPostId) return false;
        if (mLike != postDSO.mLike) return false;
        if (mDislike != postDSO.mDislike) return false;
        if (mOwner != null ? !mOwner.equals(postDSO.mOwner) : postDSO.mOwner != null) return false;
        if (mImage != null ? !mImage.equals(postDSO.mImage) : postDSO.mImage != null) return false;
        if (mDate != null ? !mDate.equals(postDSO.mDate) : postDSO.mDate != null) return false;
        return mText != null ? mText.equals(postDSO.mText) : postDSO.mText == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mPostId ^ (mPostId >>> 32));
        result = 31 * result + (mOwner != null ? mOwner.hashCode() : 0);
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        result = 31 * result + mLike;
        result = 31 * result + mDislike;
        return result;
    }

    @Override
    public String toString() {
        return "PostDSO{" +
                "mKey='" + mKey + '\'' +
                ", mPostId=" + mPostId +
                ", mOwner=" + mOwner +
                ", mImage='" + mImage + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mText='" + mText + '\'' +
                ", mLike=" + mLike +
                ", mDislike=" + mDislike +
                ", mIsLike=" + mIsLike +
                '}';
    }
}
