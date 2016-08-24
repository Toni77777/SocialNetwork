package by.grodno.toni7777.socialnetwork.ui.group;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.ui.groups.GroupsFragment;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
//        long groupId = getIntent().getLongExtra(Constants.SHARE_GROUP_ID, -1);
//        Fragment groupFragment = new GroupsFragment();
//        Bundle idBundle = new Bundle();
//        idBundle.putLong(Constants.SHARE_GROUP_ID, groupId);
//        groupFragment.setArguments(idBundle);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.content, groupFragment)
//                    .commit();
//        }
    }
}
