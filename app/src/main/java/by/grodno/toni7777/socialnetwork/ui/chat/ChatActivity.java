package by.grodno.toni7777.socialnetwork.ui.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.DrawerActivity;
import by.grodno.toni7777.socialnetwork.ui.dialogs.ShareDate;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class ChatActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

//        ShareDate shareDate = getIntent().getParcelableExtra(Constants.SHARE_CHAT_ID);
//
//        Fragment chatFragment = new ChatFragment();
//        Bundle idBundle = new Bundle();
//        idBundle.putParcelable(Constants.SHARE_CHAT_ID, shareDate);
//        chatFragment.setArguments(idBundle);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.content, chatFragment)
//                    .commit();
//        }

        long cahtId = getIntent().getLongExtra(Constants.SHARE_CHAT_ID, -1);
        Fragment groupFragment = new ChatFragment();
        Bundle idBundle = new Bundle();
        idBundle.putLong(Constants.SHARE_CHAT_ID, cahtId);
        groupFragment.setArguments(idBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, groupFragment)
                    .commit();
        }
    }
}
