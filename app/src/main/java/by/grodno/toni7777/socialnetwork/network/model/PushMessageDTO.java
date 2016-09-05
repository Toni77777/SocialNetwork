package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PushMessageDTO {

    @SerializedName("message")
    private String mMessage;

    public PushMessageDTO(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public static final String MESSAGE = "message";
}
