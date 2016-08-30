package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PushPostDTO {

    @SerializedName("text")
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PushPostDTO that = (PushPostDTO) o;

        return mText != null ? mText.equals(that.mText) : that.mText == null;

    }

    @Override
    public int hashCode() {
        return mText != null ? mText.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PushPostDTO{" +
                "mText='" + mText + '\'' +
                '}';
    }

    public static String message = "text";
}
