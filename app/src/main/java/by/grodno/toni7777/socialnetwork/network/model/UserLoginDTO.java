package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class UserLoginDTO {

    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("refresh_token")
    private String mRefreshToken;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String accessToken, String refreshToken) {
        mAccessToken = accessToken;
        mRefreshToken = refreshToken;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLoginDTO that = (UserLoginDTO) o;

        if (mAccessToken != null ? !mAccessToken.equals(that.mAccessToken) : that.mAccessToken != null)
            return false;
        return mRefreshToken != null ? mRefreshToken.equals(that.mRefreshToken) : that.mRefreshToken == null;

    }

    @Override
    public int hashCode() {
        int result = mAccessToken != null ? mAccessToken.hashCode() : 0;
        result = 31 * result + (mRefreshToken != null ? mRefreshToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "mAccessToken='" + mAccessToken + '\'' +
                ", mRefreshToken='" + mRefreshToken + '\'' +
                '}';
    }
}
