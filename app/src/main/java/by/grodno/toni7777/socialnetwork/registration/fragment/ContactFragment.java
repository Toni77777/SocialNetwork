package by.grodno.toni7777.socialnetwork.registration.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.grodno.toni7777.socialnetwork.R;

public class ContactFragment extends TabFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_contact, container, false);
    }

    @Override
    public void showErrors(SparseIntArray errors) {

    }

    @Override
    public void nextTab() {

    }
}
