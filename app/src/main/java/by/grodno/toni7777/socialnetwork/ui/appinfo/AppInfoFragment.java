package by.grodno.toni7777.socialnetwork.ui.appinfo;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.Constants.DATE_APP_INFO;

public class AppInfoFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_info);
        findPreference(getString(R.string.preference_version_name)).setSummary(BuildConfig.VERSION_NAME);
        findPreference(getString(R.string.preference_build_number)).setSummary(String.valueOf(BuildConfig.VERSION_CODE));
        findPreference(getString(R.string.preference_build_date))
                .setSummary(new SimpleDateFormat(DATE_APP_INFO).format(new Date()));
    }
}
