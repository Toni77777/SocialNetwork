package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import static by.grodno.toni7777.socialnetwork.util.Constants.SHARE_PROFILE;
import static by.grodno.toni7777.socialnetwork.util.Util.hasKeySparseIntArray;
import static by.grodno.toni7777.socialnetwork.util.Util.showErrorMessage;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_EMAIL;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_LOGIN;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_PASSWORD;
import static by.grodno.toni7777.socialnetwork.util.Validation.validateLogin;
import static by.grodno.toni7777.socialnetwork.util.Util.inNotEmptySparseIntArray;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpTabFragment;
import by.grodno.toni7777.socialnetwork.base.event.RegistrationEvent;
import by.grodno.toni7777.socialnetwork.network.model.ProfileRegistrationDTO;

public class ContactFragment extends BaseMvpTabFragment<ContactMVP.View, ContactPresenter>
        implements ContactMVP.View {

    @BindView(R.id.login_layout)
    TextInputLayout mLoginView;

    @BindView(R.id.password_layout)
    TextInputLayout mPasswordView;

    @BindView(R.id.confirm_password_layout)
    TextInputLayout mConfirmPassView;

    @BindView(R.id.mail_layout)
    TextInputLayout mMailView;

    @BindView(R.id.navigation_switcher)
    ViewSwitcher mNavigationView;

    @BindView(R.id.progress)
    ProgressBar mProgressView;

    @BindView(R.id.contact_layout)
    android.view.View mContactView;

    @Inject
    ContactPresenter mPresenter;

    private ProfileRegistrationDTO mProfile;
    private static final String STATE_PROFILE = "profile";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(SHARE_PROFILE)) {
            mProfile = bundle.getParcelable(SHARE_PROFILE);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_PROFILE)) {
                mProfile = savedInstanceState.getParcelable(STATE_PROFILE);
            }
        }
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_login, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        resetErrorAfterChange(mLoginView, mPasswordView, mConfirmPassView, mMailView);
        mNavigationView.showNext();

        if (BuildConfig.DEBUG) {
            mLoginView.getEditText().setText("Toni777");
            mPasswordView.getEditText().setText("password");
            mConfirmPassView.getEditText().setText("password");
            mMailView.getEditText().setText("toxa95401@gmail.com");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_PROFILE, mProfile);
    }

    @Override
    @OnClick(R.id.complete)
    public void complete() {
        String login = mLoginView.getEditText().getText().toString();
        String password = mPasswordView.getEditText().getText().toString();
        String confirmPass = mConfirmPassView.getEditText().getText().toString();
        String email = mMailView.getEditText().getText().toString();
        SparseIntArray errors = validateLogin(login, password, confirmPass, email);
        if (inNotEmptySparseIntArray(errors)) {
            showErrors(errors);
        } else {
            mProfile.setLogin(login);
            mProfile.setPassword(password);
            mProfile.setEmail(email);
            presenter.registration(mProfile);
        }
    }

    @Override
    public void showErrors(SparseIntArray errors) {
        if (hasKeySparseIntArray(errors, ERROR_LOGIN)) {
            int errorType = errors.get(ERROR_LOGIN);
            showErrorMessage(mLoginView, errorType);
        }
        if (hasKeySparseIntArray(errors, ERROR_PASSWORD)) {
            int errorType = errors.get(ERROR_PASSWORD);
            showErrorMessage(mPasswordView, errorType);
        }
        if (hasKeySparseIntArray(errors, ERROR_EMAIL)) {
            int errorType = errors.get(ERROR_EMAIL);
            showErrorMessage(mMailView, errorType);
        }
    }

    @Override
    public void showContactForm() {
        ((ContactViewState) viewState).setShowLoginForm();
        mContactView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showError() {
        ((ContactViewState) viewState).setShowError();
        mProgressView.setVisibility(android.view.View.GONE);
        mContactView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showLoading() {
        ((ContactViewState) viewState).setShowLoading();
        mContactView.setVisibility(android.view.View.GONE);
        mProgressView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public ViewState createViewState() {
        setRetainInstance(true);
        return new ContactViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showContactForm();
    }

    @Override
    public ContactPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void registrationSuccess() {
        EventBus.getDefault().post(new RegistrationEvent(mProfile.getName()));
        getActivity().finish();
    }
}
