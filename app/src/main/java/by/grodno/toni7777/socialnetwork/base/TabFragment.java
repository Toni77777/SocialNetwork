package by.grodno.toni7777.socialnetwork.base;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.SparseIntArray;

import by.grodno.toni7777.socialnetwork.ui.registration.ErrorTextWatcher;

public abstract class TabFragment extends BaseFragment implements Tabs {

    @Override
    public abstract void showErrors(SparseIntArray errors);

    @Override
    public void nextTab() {
    }

    @Override
    public void complete() {
    }

    @Override
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
