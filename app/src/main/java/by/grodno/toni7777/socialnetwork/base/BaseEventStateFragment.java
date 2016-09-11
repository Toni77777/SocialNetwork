package by.grodno.toni7777.socialnetwork.base;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseEventStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseViewStateFragment<CV, M, V, P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
