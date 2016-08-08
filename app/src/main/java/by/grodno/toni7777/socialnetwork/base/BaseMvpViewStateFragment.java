package by.grodno.toni7777.socialnetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpViewStateFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpViewStateFragment<V, P> {

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
}
