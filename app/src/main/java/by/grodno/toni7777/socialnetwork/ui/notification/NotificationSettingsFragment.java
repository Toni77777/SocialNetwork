package by.grodno.toni7777.socialnetwork.ui.notification;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import by.grodno.toni7777.socialnetwork.R;

public class NotificationSettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.notification_settings);

    }
}
