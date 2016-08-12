package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class AuthorizationDTO {

    @SerializedName("access_token")
    private String mAccessToken;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationDTO userDTO = (AuthorizationDTO) o;

        return mAccessToken != null ? mAccessToken.equals(userDTO.mAccessToken) : userDTO.mAccessToken == null;

    }

    @Override
    public int hashCode() {
        return mAccessToken != null ? mAccessToken.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AuthorizationDTO{" +
                "mAccessToken='" + mAccessToken + '\'' +
                '}';
    }
}
