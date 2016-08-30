package by.grodno.toni7777.socialnetwork.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.ui.appinfo.AppInfoActivity;
import by.grodno.toni7777.socialnetwork.ui.notification.NotificationSettingsActivity;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        findPreference(getString(R.string.preference_settings_notification))
                .setOnPreferenceClickListener(preference -> {
                    startActivity(new Intent(getActivity(), NotificationSettingsActivity.class));
                    return false;
                });

        findPreference(getString(R.string.preference_app_info))
                .setOnPreferenceClickListener(preference -> {
                    startActivity(new Intent(getActivity(), AppInfoActivity.class));
                    return false;
                });
    }
}
