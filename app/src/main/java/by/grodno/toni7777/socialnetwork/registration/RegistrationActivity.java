package by.grodno.toni7777.socialnetwork.registration;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.ToolbarActivity;
import by.grodno.toni7777.socialnetwork.registration.fragment.InfoFragment;
import by.grodno.toni7777.socialnetwork.registration.fragment.LoginFragment;

public class RegistrationActivity extends ToolbarActivity
        implements InfoFragment.OnInfoPass, LoginFragment.OnLoginPass {

    private FragmentManager mFragmentManager;

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
    }

    @Override
    public void onInfoPass(String name, String surname, String sex, String dateBirth) {
        Log.e("Info", name + surname + sex + dateBirth);

        mFragmentManager.beginTransaction()
                .replace(R.id.content, new LoginFragment())
                .commit();
    }


    @Override
    public void onLoginPass(String login, String password, String email) {
        Log.e("Login", login + password + email);

    }
}
