package by.grodno.toni7777.socialnetwork.registration.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.grodno.toni7777.socialnetwork.R;

public class LoginTab extends Tab {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_login, container, false);
    }

    @Override
    public void showErrors(SparseIntArray errors) {

    }
}
