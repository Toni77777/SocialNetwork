package by.grodno.toni7777.socialnetwork.wall;

import android.os.Bundle;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.ToolbarActivity;

public class WallActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new WallFragment())
                    .commit();
        }
    }
}
