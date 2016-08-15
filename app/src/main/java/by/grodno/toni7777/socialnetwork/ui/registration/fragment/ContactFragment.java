package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseMvpTabFragment;
import by.grodno.toni7777.socialnetwork.ui.registration.Profile;

import static by.grodno.toni7777.socialnetwork.util.Constants.SHARE_PROFILE;
import static by.grodno.toni7777.socialnetwork.util.Util.inNotEmptySparseIntArray;
import static by.grodno.toni7777.socialnetwork.util.Validation.validateContact;


public class ContactFragment extends BaseMvpTabFragment<ContactMVP.ContactView, ContactPresenter>
        implements ContactMVP.ContactView {

    @BindView(R.id.phone_layout)
    TextInputLayout mPhoneView;

    @BindView(R.id.skype_layout)
    TextInputLayout mSkypeView;

    @BindView(R.id.city_layout)
    TextInputLayout mCityView;

    @BindView(R.id.navigation_switcher)
    ViewSwitcher mNavigationView;

    @BindView(R.id.progress)
    ProgressBar mProgressView;

    @BindView(R.id.contact_layout)
    View mContactView;

    @Inject
    ContactPresenter mPresenter;

    private Profile mProfile;
    private static final String STATE_PROFILE = "profile";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(SHARE_PROFILE)) {
            mProfile = bundle.getParcelable(SHARE_PROFILE);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_PROFILE)) {
                mProfile = savedInstanceState.getParcelable(STATE_PROFILE);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_contact, container, false);
    }

    @Override
    public ContactPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavigationView.showNext();
        resetErrorAfterChange(mPhoneView, mSkypeView, mCityView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_PROFILE, mProfile);
    }

    @Override
    @OnClick(R.id.complete)
    public void complete() {
        String phone = mPhoneView.getEditText().getText().toString();
        String skype = mSkypeView.getEditText().getText().toString();
        String city = mCityView.getEditText().getText().toString();
        SparseIntArray errors = validateContact(phone, skype, city);
        if (inNotEmptySparseIntArray(errors)) {
            showErrors(errors);
        } else {
            mContactView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
            // request registration
            mProfile.setTelephone(phone);// need int
            mProfile.setSkype(skype);
            mProfile.setCity(city);
            presenter.registration(mProfile);
        }
    }

    @Override
    public void showErrors(SparseIntArray errors) {

    }

    @Override
    public ViewState createViewState() {
        return null;
    }

    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    public void showContactForm() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void registrationSuccess() {
        Log.e("TAG", "Registration");
    }
}
