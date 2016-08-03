package by.grodno.toni7777.socialnetwork.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.wall.WallActivity;

public class DrawerActivity extends ToolbarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_wall) {
            startSectionActivity(WallActivity.class);
        } else if (id == R.id.nav_friends) {
//            startSectionActivity(FriendsActivity.class);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    protected void startSectionActivity(Class<? extends Activity> clas) {
        if (clas == this.getClass()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            startActivity(IntentCompat.makeRestartActivityTask(new ComponentName(this, clas)));
        }
    }
}
