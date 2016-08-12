package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;

public final class LoginUtil {

    public static boolean isLogined(Context context) {
        String token = new LoginPreferences(context).getAccessToken();
        return !token.equals(Constants.EMPTY_STRING);
    }
}
