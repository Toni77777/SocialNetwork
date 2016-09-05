package by.grodno.toni7777.socialnetwork.ui.model;

public class PostDVO {

    private long mPostId;
    private OwnerDVO mOwner;
    private String mImage;
    private String mDate;
    private String mText;
    private int mLike;
    private int mDislike;
    private Integer mIsLike;

    public PostDVO() {
    }

    public PostDVO(long postId, OwnerDVO owner, String image, String date, String text, int like,
                   int dislike, Integer isLike) {
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

    public OwnerDVO getOwner() {
        return mOwner;
    }

    public void setOwner(OwnerDVO owner) {
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

        PostDVO postDVO = (PostDVO) o;

        if (mPostId != postDVO.mPostId) return false;
        if (mOwner != null ? !mOwner.equals(postDVO.mOwner) : postDVO.mOwner != null) return false;
        if (mImage != null ? !mImage.equals(postDVO.mImage) : postDVO.mImage != null) return false;
        return mText != null ? mText.equals(postDVO.mText) : postDVO.mText == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mPostId ^ (mPostId >>> 32));
        result = 31 * result + (mOwner != null ? mOwner.hashCode() : 0);
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostDVO{" +
                "mPostId=" + mPostId +
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
