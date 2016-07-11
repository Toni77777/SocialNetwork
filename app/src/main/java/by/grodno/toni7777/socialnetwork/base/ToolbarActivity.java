package by.grodno.toni7777.socialnetwork.base;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;

public class ToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
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
