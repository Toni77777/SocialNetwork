package by.grodno.toni7777.socialnetwork.registration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.grodno.toni7777.socialnetwork.R;

public class LoginFragment extends TabFragment {

    private OnLoginPass mLoginPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_login, container, false);
    }

    @Override
    public void showErrors(SparseIntArray errors) {

    }

    @Override
    public void nextTab() {
//        mLoginPass.onInfoPass();
        mLoginPass.onLoginPass("Login", "Pass", "Email");
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
