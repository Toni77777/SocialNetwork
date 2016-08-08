package by.grodno.toni7777.socialnetwork.base;

import org.greenrobot.eventbus.EventBus;

public class BaseEventFragment extends BaseFragment {

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
