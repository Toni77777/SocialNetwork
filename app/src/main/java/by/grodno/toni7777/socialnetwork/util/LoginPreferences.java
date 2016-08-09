package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferences {

    private static final String LOGIN_PREFERENCES = "saveAccessToken";
    private static final String TOKEN_PREFERENCES = "accessToken";
    private SharedPreferences mTokenPreferences;

    public LoginPreferences(Context context) {
        mTokenPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setAccessToken(String token) {
        mTokenPreferences.edit().putString(TOKEN_PREFERENCES, token).apply();
    }

    public String getAccessToken() {
        return mTokenPreferences.getString(TOKEN_PREFERENCES, "");
    }
}