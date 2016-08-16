package by.grodno.toni7777.socialnetwork.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        findPreference(getString(R.string.preference_version_name)).setSummary(BuildConfig.VERSION_NAME);
        findPreference(getString(R.string.preference_build_number)).setSummary(String.valueOf(BuildConfig.VERSION_CODE));
    }
}
