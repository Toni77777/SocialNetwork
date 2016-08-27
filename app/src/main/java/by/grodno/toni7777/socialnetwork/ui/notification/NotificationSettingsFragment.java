package by.grodno.toni7777.socialnetwork.ui.notification;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.util.SettingsUtil;

public class NotificationSettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.notification_settings);

        SettingsUtil settings = new SettingsUtil(getActivity());

        findPreference(getString(R.string.preference_notification)).setOnPreferenceChangeListener((preference, object) -> {
            settings.setShowNotification((boolean) object);
            Log.e("Notif", "Notification " + (boolean) object);
            return true;
        });

        findPreference(getString(R.string.preference_sound)).setOnPreferenceChangeListener((preference, object) -> {
            settings.setNotificationSound((boolean) object);
            Log.e("Notif", "Notification sound" + (boolean) object);
            return true;
        });

        findPreference(getString(R.string.preference_vibration)).setOnPreferenceChangeListener((preference, object) -> {
            settings.setNotificationVibrate((boolean) object);
            Log.e("Notif", "Notification vibration" + (boolean) object);
            return true;
        });

    }
}
