package by.grodno.toni7777.socialnetwork.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.test.UserLogin;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.login)
    EditText loginInfo;
    @BindView(R.id.password)
    EditText passInfo;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this, ((SocialNetworkApp) getApplication()).getNetworkService());
    }

    @OnClick(R.id.sing_in)
    void singIn() {
        String login = loginInfo.getText().toString();
        String password = passInfo.getText().toString();
        presenter.loginRequest(login, password);
    }

    @Override
    public void loginSuccess(UserLogin userLogin) {
        Log.e("USER", userLogin.toString());
    }

    @Override
    public void loginError(Throwable e) {
        Log.e("USER", e.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.rxUnSubscribe();
    }
}
