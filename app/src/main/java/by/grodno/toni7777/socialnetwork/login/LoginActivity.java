package by.grodno.toni7777.socialnetwork.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText mLoginInfo;
    @BindView(R.id.password)
    EditText mPassInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.sing_in)
    void singIn() {

    }
}
