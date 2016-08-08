package by.grodno.toni7777.socialnetwork.base;

import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseEventStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseViewStateFragment<CV, M, V, P> {

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
