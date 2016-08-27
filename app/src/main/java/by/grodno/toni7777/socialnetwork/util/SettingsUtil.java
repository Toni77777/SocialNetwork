package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.content.SharedPreferences;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

public class SettingsUtil {

    private SharedPreferences mNotificationSettings;

    public SettingsUtil(Context context) {
        mNotificationSettings = context.getSharedPreferences(Constants.SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setShowNotification(boolean showNotification) {
        mNotificationSettings.edit().putBoolean(NOTIFICATION_PREFERENCES, showNotification).apply();
    }

    public boolean getShowNotification() {
        return mNotificationSettings.getBoolean(NOTIFICATION_PREFERENCES, true);
    }

    public void setNotificationSound(boolean soundNotification) {
        mNotificationSettings.edit().putBoolean(NOTIFICATION_SOUND_PREFERENCES, soundNotification).apply();
    }

    public boolean getNotificationSound() {
        return mNotificationSettings.getBoolean(NOTIFICATION_SOUND_PREFERENCES, true);
    }

    public void setNotificationVibrate(boolean vibrateNotification) {
        mNotificationSettings.edit().putBoolean(NOTIFICATION_VIBRATE_PREFERENCES, vibrateNotification).apply();
    }

    public boolean getNotificationVibrate() {
        return mNotificationSettings.getBoolean(NOTIFICATION_VIBRATE_PREFERENCES, true);
    }
}
