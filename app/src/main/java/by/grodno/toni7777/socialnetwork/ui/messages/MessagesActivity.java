package by.grodno.toni7777.socialnetwork.ui.messages;

import android.os.Bundle;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.DrawerActivity;

public class MessagesActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new MessagesFragment())
                    .commit();
        }
    }
}
