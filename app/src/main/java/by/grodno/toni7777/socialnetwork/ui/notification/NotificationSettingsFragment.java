package by.grodno.toni7777.socialnetwork.ui.notification;

import android.os.Bundle;
import android.preference.PreferenceFragment;

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
            return true;
        });

        findPreference(getString(R.string.preference_sound)).setOnPreferenceChangeListener((preference, object) -> {
            settings.setNotificationSound((boolean) object);
            return true;
        });

        findPreference(getString(R.string.preference_vibration)).setOnPreferenceChangeListener((preference, object) -> {
            settings.setNotificationVibrate((boolean) object);
            return true;
        });

    }
}
