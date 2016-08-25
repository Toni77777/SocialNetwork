package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class NotificationDTO {

    @SerializedName("token")
    private String mToken;

    public NotificationDTO() {
    }

    public NotificationDTO(String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "mToken='" + mToken + '\'' +
                '}';
    }
}
