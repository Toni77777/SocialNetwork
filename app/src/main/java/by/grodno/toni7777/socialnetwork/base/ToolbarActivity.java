package by.grodno.toni7777.socialnetwork.base;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import by.grodno.toni7777.socialnetwork.R;

public class ToolbarActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getTitle());
            setSupportActionBar(mToolbar);
        }
    }

    @Nullable
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
