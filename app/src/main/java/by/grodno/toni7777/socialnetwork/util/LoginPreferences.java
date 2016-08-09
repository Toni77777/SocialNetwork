package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferences {

    private static final String LOGIN_PREFERENCES = "saveAccessToken";
    private static final String TOKEN_PREFERENCES = "accessToken";
    private static final String USER_ID_PREFERENCES = "userId";
    private SharedPreferences mUserPreferences;

    public LoginPreferences(Context context) {
        mUserPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setAccessToken(String token) {
        mUserPreferences.edit().putString(TOKEN_PREFERENCES, token).apply();
    }

    public String getAccessToken() {
        return mUserPreferences.getString(TOKEN_PREFERENCES, "");
    }

    public long getUserId() {
        return mUserPreferences.getLong(USER_ID_PREFERENCES, -1);
    }

    public void setUserId(long userId) {
        mUserPreferences.edit().putLong(USER_ID_PREFERENCES, userId).apply();
    }
}