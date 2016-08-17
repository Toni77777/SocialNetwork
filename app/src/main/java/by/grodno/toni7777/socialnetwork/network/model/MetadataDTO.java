package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class MetadataDTO {

    @SerializedName("limit")
    private int mLimit;

    @SerializedName("count")
    private int mCount;

    @SerializedName("offset")
    private int mOffset;

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataDTO that = (MetadataDTO) o;

        if (mLimit != that.mLimit) return false;
        if (mCount != that.mCount) return false;
        return mOffset == that.mOffset;

    }

    @Override
    public int hashCode() {
        int result = mLimit;
        result = 31 * result + mCount;
        result = 31 * result + mOffset;
        return result;
    }

    @Override
    public String toString() {
        return "MetadataDTO{" +
                "mLimit=" + mLimit +
                ", mCount=" + mCount +
                ", mOffset=" + mOffset +
                '}';
    }
}
