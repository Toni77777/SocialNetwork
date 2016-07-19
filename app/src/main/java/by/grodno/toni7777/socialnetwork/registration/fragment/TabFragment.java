package by.grodno.toni7777.socialnetwork.registration.fragment;

import android.util.SparseIntArray;

import by.grodno.toni7777.socialnetwork.base.BaseFragment;

public abstract class TabFragment extends BaseFragment {

    public abstract void showErrors(SparseIntArray errors);

    public abstract void nextTab();

}
