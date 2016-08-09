package by.grodno.toni7777.socialnetwork.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.registration.RegistrationActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.wall.WallActivity;


public class LoginFragment extends BaseMvpViewStateFragment<LoginView, LoginPresenter>
        implements LoginView {

    @BindView(R.id.login)
    EditText mLoginView;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.error)
    TextView mErrorView;

    @BindView(R.id.sing_in)
    ActionProcessButton mAuthorizationButton;

    @BindView(R.id.sing_up)
    ActionProcessButton mRegistrationButton;

    @BindView(R.id.forgot_password)
    TextView mForgotPassView;

    @Inject
    LoginPresenter mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (isLoggedIn()) {
            getContext().startActivity(new Intent(getContext(), WallActivity.class));
            getActivity().finish();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getNetworkComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        mLoginView.setText(BuildConfig.LOGIN);
        mPasswordView.setText(BuildConfig.PASS);
        mAuthorizationButton.setMode(ActionProcessButton.Mode.ENDLESS);
    }

    @OnClick(R.id.sing_in)
    void singIn() {
        String login = mLoginView.getText().toString();
        String pass = mPasswordView.getText().toString();
        mPresenter.authorization(login, pass);
    }

    @OnClick(R.id.sing_up)
    void singUn() {
        getContext().startActivity(new Intent(getContext(), RegistrationActivity.class));
        mErrorView.setVisibility(View.GONE);
    }

    @OnClick(R.id.forgot_password)
    void restorePassword() {
        // TODO start restore activity
    }

    private boolean isLoggedIn() {
        // TODO check autoriration
        return true;
    }

    @Override
    public void showError() {
        ((LoginViewState) viewState).setShowError();
        setViewsEnabled(true);
        mAuthorizationButton.setProgress(Constants.ACTION_BUTTON_START);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        ((LoginViewState) viewState).setShowLoading();
        setViewsEnabled(false);
        mErrorView.setVisibility(View.GONE);
        mAuthorizationButton.setProgress(ACTION_BUTTON_PROGRESS);
    }

    @Override
    public void loginSuccess() {
        mAuthorizationButton.setProgress(ACTION_BUTTON_FINISH);
        getContext().startActivity(new Intent(getContext(), WallActivity.class));
        getActivity().finish();
    }

    @Override
    public LoginPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public ViewState createViewState() {
        return new LoginViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showLoginForm();
    }

    @Override
    public void showLoginForm() {
        ((LoginViewState) viewState).setShowLoginForm();
        mErrorView.setVisibility(View.GONE);
        setViewsEnabled(true);
        mAuthorizationButton.setProgress(ACTION_BUTTON_START);
    }

    private void setViewsEnabled(boolean enabled) {
        mLoginView.setEnabled(enabled);
        mPasswordView.setEnabled(enabled);
        methot(enabled);
    }

    private void methot(boolean enabled) {
        mRegistrationButton.setEnabled(enabled);
        mForgotPassView.setEnabled(enabled);
    }
}
