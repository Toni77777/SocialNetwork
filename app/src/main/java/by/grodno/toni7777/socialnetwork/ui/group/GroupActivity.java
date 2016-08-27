package by.grodno.toni7777.socialnetwork.ui.group;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.DrawerActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class GroupActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        long groupId = getIntent().getLongExtra(Constants.SHARE_GROUP_ID, -1);
        Fragment groupFragment = new GroupFragment();
        Bundle idBundle = new Bundle();
        idBundle.putLong(Constants.SHARE_GROUP_ID, groupId);
        groupFragment.setArguments(idBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, groupFragment)
                    .commit();
        }
    }
}
