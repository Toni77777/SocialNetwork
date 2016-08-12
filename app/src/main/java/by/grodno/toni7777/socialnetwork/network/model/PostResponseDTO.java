package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PostResponseDTO {

    @SerializedName("entity")
    private boolean mSuccess;

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }

    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "mSuccess=" + mSuccess +
                '}';
    }
}
