package by.grodno.toni7777.socialnetwork.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.RegistrationEvent;
import by.grodno.toni7777.socialnetwork.ui.registration.RegistrationActivity;
import by.grodno.toni7777.socialnetwork.ui.wall.WallActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.LoginUtil;


public class LoginFragment extends BaseMvpViewStateFragment<LoginMVP.View, LoginPresenter>
        implements LoginMVP.View {

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
        // TODO uncomment and add refresh token method
        if (LoginUtil.isLogined(getContext())) {
            getContext().startActivity(new Intent(getContext(), WallActivity.class));
            getActivity().finish();
        }
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
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
        mErrorView.setVisibility(android.view.View.GONE);
    }

    @OnClick(R.id.forgot_password)
    void restorePassword() {
        // TODO start restore activity
    }

    @Override
    public void showError() {
        ((LoginViewState) viewState).setShowError();
        setViewsEnabled(true);
        mAuthorizationButton.setProgress(Constants.ACTION_BUTTON_START);
        mErrorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showLoading() {
        ((LoginViewState) viewState).setShowLoading();
        setViewsEnabled(false);
        mErrorView.setVisibility(android.view.View.GONE);
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
        mErrorView.setVisibility(android.view.View.GONE);
        setViewsEnabled(true);
        mAuthorizationButton.setProgress(ACTION_BUTTON_START);
    }

    private void setViewsEnabled(boolean enabled) {
        mLoginView.setEnabled(enabled);
        mPasswordView.setEnabled(enabled);
        mRegistrationButton.setEnabled(enabled);
        mForgotPassView.setEnabled(enabled);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void registrationSuccess(RegistrationEvent event) {
        Snackbar.make(mForgotPassView, "Welcome " + event.getNameUser() + " registration success ", Snackbar.LENGTH_LONG)
                .setDuration(5000)
                .show();
    }

}
