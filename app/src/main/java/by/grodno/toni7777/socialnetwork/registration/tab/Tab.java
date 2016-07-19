package by.grodno.toni7777.socialnetwork.registration.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

public abstract class Tab extends AbstractStep {

    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public String name() {
        return getArguments().getString(TAB_TITLE_SHARE);
    }

    @Override
    public String error() {
        return getArguments().getString(TAB_ERROR_SHARE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public abstract void showErrors(SparseIntArray errors);
}
