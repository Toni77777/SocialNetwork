package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDTO {

    @SerializedName("entity")
    private boolean mSuccess;

    @SerializedName("error")
    private String mError;

    @SerializedName("message")
    private String mMessage;

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "mSuccess=" + mSuccess +
                ", mError='" + mError + '\'' +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}
