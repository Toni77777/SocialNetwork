package by.grodno.toni7777.socialnetwork.base;

import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;

public interface Tabs {

    void showErrors(SparseIntArray errors);

    void nextTab();


    void complete();


    void resetErrorAfterChange(TextInputLayout... layouts);
}
