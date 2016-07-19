package by.grodno.toni7777.socialnetwork.registration;

import android.os.Bundle;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.registration.tab.ContactTab;
import by.grodno.toni7777.socialnetwork.registration.tab.InfoTab;
import by.grodno.toni7777.socialnetwork.registration.tab.LoginTab;

public class RegistrationActivity extends TabStepper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLinear(true);
        setPreviousVisible();
        setDisabledTouch();
        addStep(createFragmentWithTitle(new InfoTab(), getString(R.string.tab_title_info)));
        addStep(createFragmentWithTitle(new LoginTab(), getString(R.string.tab_title_login)));
        addStep(createFragmentWithTitle(new ContactTab(), getString(R.string.tab_title_contact)));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onComplete() {
        super.onComplete();
//        finish();
    }

    private AbstractStep createFragmentWithTitle(AbstractStep fragment, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TAB_TITLE_SHARE, title);
        bundle.putString(TAB_ERROR_SHARE, getString(R.string.tab_error_message));
        fragment.setArguments(bundle);
        return fragment;
    }
}
