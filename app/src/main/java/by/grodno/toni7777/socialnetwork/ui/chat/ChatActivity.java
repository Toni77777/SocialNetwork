package by.grodno.toni7777.socialnetwork.ui.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.DrawerActivity;
import by.grodno.toni7777.socialnetwork.ui.model.ChatDataDVO;
import by.grodno.toni7777.socialnetwork.util.Constants;

public class ChatActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ChatDataDVO chatData = getIntent().getParcelableExtra(Constants.SHARE_CHAT_ID);
        Fragment chatFragment = new ChatFragment();
        Bundle chatBundle = new Bundle();
        chatBundle.putParcelable(Constants.SHARE_CHAT_ID, chatData);
        chatFragment.setArguments(chatBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, chatFragment)
                    .commit();
        }
    }
}
