package by.grodno.toni7777.socialnetwork.ui.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.ToolbarActivity;
import by.grodno.toni7777.socialnetwork.network.model.ProfileRegistrationDTO;
import by.grodno.toni7777.socialnetwork.ui.registration.fragment.ContactFragment;
import by.grodno.toni7777.socialnetwork.ui.registration.fragment.InfoFragment;

import static by.grodno.toni7777.socialnetwork.util.Constants.SHARE_PROFILE;

public class RegistrationActivity extends ToolbarActivity
        implements InfoFragment.OnInfoPass {

    private FragmentManager mFragmentManager;
    private ProfileRegistrationDTO mProfile;
    private static final String STATE_PROFILE = "profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.content, new InfoFragment())
                    .commit();
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_PROFILE)) {
                mProfile = savedInstanceState.getParcelable(STATE_PROFILE);
            }
        } else {
            mProfile = new ProfileRegistrationDTO();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_PROFILE, mProfile);
    }

    @Override
    public void onInfoPass(String name, String surname, String sex, String dateBirth) {
        mProfile.setName(name);
        mProfile.setSurname(surname);
        mProfile.setSex(sex);
        mProfile.setDateBirth(dateBirth);

        Fragment contactFragment = new ContactFragment();
        Bundle profile = new Bundle();
        profile.putParcelable(SHARE_PROFILE, mProfile);
        contactFragment.setArguments(profile);

        mFragmentManager.beginTransaction()
                .replace(R.id.content, contactFragment)
                .commit();
    }
}
