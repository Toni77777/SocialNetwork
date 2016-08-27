package by.grodno.toni7777.socialnetwork.ui.friend;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.DrawerActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class FriendActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        long friendId = getIntent().getLongExtra(Constants.SHARE_FRIEND_ID, -1);
        Fragment friendFragment = new FriendFragment();
        Bundle idBundle = new Bundle();
        idBundle.putLong(Constants.SHARE_FRIEND_ID, friendId);
        friendFragment.setArguments(idBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, friendFragment)
                    .commit();
        }
    }
}
