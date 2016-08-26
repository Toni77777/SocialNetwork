package by.grodno.toni7777.socialnetwork.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import by.grodno.toni7777.socialnetwork.BuildConfig;
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
//        findPreference(getString(R.string.preference_version_name)).setSummary(BuildConfig.VERSION_NAME);
//
//        Preference p = findPreference(getString(R.string.preference_version_name));
//        p.setSummary(BuildConfig.VERSION_NAME);
//
//        p.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                Log.e("Tag", "Error settigngs work");
//                return false;
//            }
//        });
//
//
//        findPreference(getString(R.string.preference_build_number)).setSummary(String.valueOf(BuildConfig.VERSION_CODE));
    }
}
