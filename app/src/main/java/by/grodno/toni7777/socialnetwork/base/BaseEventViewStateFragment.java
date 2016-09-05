package by.grodno.toni7777.socialnetwork.base;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import org.greenrobot.eventbus.EventBus;

/**
 * Need use register unregister from onCreate()/onDestroy() because wail event when View onPause()
 * Override getErrorMessage() because some class don't use this method, but method must override (Mosby)
 *
 * @param <CV>
 * @param <M>
 * @param <V>
 * @param <P>
 */
public abstract class BaseEventViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseViewStateFragment<CV, M, V, P> {

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
