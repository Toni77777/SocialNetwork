package by.grodno.toni7777.socialnetwork.ui.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import by.grodno.toni7777.socialnetwork.util.Constants;

public class GroupFragment extends Fragment {

    private long mGroupId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(Constants.SHARE_GROUP_ID)) {
            mGroupId = bundle.getLong(Constants.SHARE_GROUP_ID);
        }
    }



}
