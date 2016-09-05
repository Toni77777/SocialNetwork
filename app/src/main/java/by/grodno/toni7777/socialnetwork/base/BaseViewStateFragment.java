package by.grodno.toni7777.socialnetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceViewStateFragment<CV, M, V, P> {

    private Unbinder mUnbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }
}
