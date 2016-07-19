package by.grodno.toni7777.socialnetwork.registration.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

public class ContactTab extends Tab {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_contact, container, false);
    }

    @Override
    public void onStepVisible() {
        super.onStepVisible();
        Bundle share = getStepData();
        if (share != null) {
            if (share.containsKey(TABS_SHARE_NAME)) {
                String name = share.getString(TABS_SHARE_NAME);
                Log.e("TAB", name);
            }
            if (share.containsKey(TABS_SHARE_SURNAME)) {
                String surname = share.getString(TABS_SHARE_SURNAME);
                Log.e("TAB", surname);
            }
            if (share.containsKey(TABS_SHARE_SEX)) {
                String sex = share.getString(TABS_SHARE_SEX);
                Log.e("TAB", sex);
            }
            if (share.containsKey(TABS_SHARE_DATE_BIRTH)) {
                String date = share.getString(TABS_SHARE_DATE_BIRTH);
                Log.e("TAB", date);
            }
        }
    }

    @Override
    public boolean nextIf() {
        return true;
    }

    @Override
    public void showErrors(SparseIntArray errors) {

    }
}
