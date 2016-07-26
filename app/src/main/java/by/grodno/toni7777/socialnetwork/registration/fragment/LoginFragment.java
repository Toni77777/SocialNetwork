package by.grodno.toni7777.socialnetwork.registration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static by.grodno.toni7777.socialnetwork.util.Util.hasKeySparseIntArray;
import static by.grodno.toni7777.socialnetwork.util.Util.showErrorMessage;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_EMAIL;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_LOGIN;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_PASSWORD;
import static by.grodno.toni7777.socialnetwork.util.Validation.validateLogin;
import static by.grodno.toni7777.socialnetwork.util.Util.inNotEmptySparseIntArray;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;

public class LoginFragment extends TabFragment {

    @BindView(R.id.login_layout)
    TextInputLayout mLoginView;

    @BindView(R.id.password_layout)
    TextInputLayout mPasswordView;

    @BindView(R.id.confirm_password_layout)
    TextInputLayout mConfirmPassView;

    @BindView(R.id.mail_layout)
    TextInputLayout mMailView;

    private OnLoginPass mLoginPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_login, container, false);
    }

    @Override
    @OnClick(R.id.next)
    public void nextTab() {
        String login = mLoginView.getEditText().getText().toString();
        String password = mPasswordView.getEditText().getText().toString();
        String confirmPass = mConfirmPassView.getEditText().getText().toString();
        String email = mMailView.getEditText().getText().toString();
        SparseIntArray errors = validateLogin(login, password, confirmPass, email);
        if (inNotEmptySparseIntArray(errors)) {
            showErrors(errors);
        } else {
            mLoginPass.onLoginPass(login, password, email);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginPass) {
            mLoginPass = (OnLoginPass) context;
        } else {
            throw new RuntimeException("Host activity must implements OnLoginPass interface.");
        }
    }

    public interface OnLoginPass {
        void onLoginPass(String login, String password, String email);
    }


}
