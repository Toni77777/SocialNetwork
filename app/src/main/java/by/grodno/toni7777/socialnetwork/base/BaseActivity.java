package by.grodno.toni7777.socialnetwork.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void startToActivity(Class<? extends Activity> toActivity) {
        startActivity(new Intent(this, toActivity));
    }
}
