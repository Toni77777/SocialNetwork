package by.grodno.toni7777.socialnetwork.registration.fragment;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.SparseIntArray;

import by.grodno.toni7777.socialnetwork.base.BaseFragment;
import by.grodno.toni7777.socialnetwork.registration.ErrorTextWatcher;

public abstract class TabFragment extends BaseFragment {

    public abstract void showErrors(SparseIntArray errors);

    public void nextTab() {
    }

    public void complete() {
    }

    public void resetErrorAfterChange(TextInputLayout... layouts) {
        for (TextInputLayout inputText : layouts) {
            inputText.getEditText().addTextChangedListener(new ErrorTextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    inputText.setError(null);
                }
            });
        }
    }
}
