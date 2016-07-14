package by.grodno.toni7777.socialnetwork.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseActivity;
import by.grodno.toni7777.socialnetwork.base.BaseFragment;
import by.grodno.toni7777.socialnetwork.test.UserLogin;
import by.grodno.toni7777.socialnetwork.wall.WallActivity;

public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    private LoginPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenterImp(this);

//        if (isLoggedIn()) {
//            ((BaseActivity) getActivity()).startToActivity(WallActivity.class);
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setText(BuildConfig.LOGIN);
        password.setText(BuildConfig.PASS);
    }

    @OnClick(R.id.sing_in)
    void singIn() {
        String loginn = login.getText().toString();
        String pass = password.getText().toString();
        presenter.loginRequest(loginn, pass);
    }

    @OnClick(R.id.sing_up)
    void singUn() {

    }

    //Need back stack activity
    @OnClick(R.id.forgot_password)
    void restorePassword() {

    }

    @Override
    public void loginSuccess(UserLogin userLogin) {
        Log.e("USER", userLogin.toString());
        ((BaseActivity) getActivity()).startToActivity(WallActivity.class);
    }

    @Override
    public void loginError(Throwable e) {
        Log.e("USER", e.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.rxUnSubscribe();
    }

    private boolean isLoggedIn() {
        return true;
    }

}
