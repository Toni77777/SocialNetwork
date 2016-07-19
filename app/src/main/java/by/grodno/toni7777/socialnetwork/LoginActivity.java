package by.grodno.toni7777.socialnetwork;

import android.os.Bundle;

import by.grodno.toni7777.socialnetwork.base.BaseActivity;
import by.grodno.toni7777.socialnetwork.di.ApplicationModule;
import by.grodno.toni7777.socialnetwork.di.DaggerApplicationComponent;
import by.grodno.toni7777.socialnetwork.login.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new LoginFragment())
                    .commit();
        }
    }
}
