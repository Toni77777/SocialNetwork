package by.grodno.toni7777.socialnetwork.ui.newpost;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.ToolbarActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class NewPostActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        long userId = getIntent().getLongExtra(Constants.SHARE_USER_ID, Constants.EMPTY_USER_ID);
        Bundle userBundle = new Bundle();
        userBundle.putLong(Constants.SHARE_USER_ID, userId);
        Fragment newPostFragment = new NewPostFragment();
        newPostFragment.setArguments(userBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, newPostFragment)
                    .commit();
        }
    }
}
