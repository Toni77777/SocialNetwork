package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.content.SharedPreferences;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

public class LoginPreferences {

    private SharedPreferences mUserPreferences;

    public LoginPreferences(Context context) {
        mUserPreferences = context.getSharedPreferences(Constants.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setAccessToken(String token) {
        mUserPreferences.edit().putString(TOKEN_PREFERENCES, token).apply();
    }

    public String getAccessToken() {
        return mUserPreferences.getString(TOKEN_PREFERENCES, EMPTY_STRING);
    }

    public long getUserId() {
        return mUserPreferences.getLong(USER_ID_PREFERENCES, EMPTY_USER_ID);
    }

    public void setUserId(long userId) {
        mUserPreferences.edit().putLong(USER_ID_PREFERENCES, userId).apply();
    }

    public String getUserAvatar() {
        return mUserPreferences.getString(USER_AVATAR_PREFERENCES, EMPTY_STRING);
    }

    public void setUserAvatar(String url) {
        mUserPreferences.edit().putString(USER_AVATAR_PREFERENCES, url).apply();
    }

    public String getUserFullName() {
        return mUserPreferences.getString(USER_FULL_NAME_PREFERENCES, EMPTY_STRING);
    }

    public void setUserFullName(String name, String surname) {
        mUserPreferences.edit().putString(USER_FULL_NAME_PREFERENCES, name + EMPTY_STRING + surname).apply();
    }

    public void clearUserLoginInformation() {
        mUserPreferences.edit().clear().apply();
    }
}