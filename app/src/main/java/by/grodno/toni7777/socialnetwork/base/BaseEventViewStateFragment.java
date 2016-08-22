package by.grodno.toni7777.socialnetwork.base;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseEventViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseViewStateFragment<CV, M, V, P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
