package by.grodno.toni7777.socialnetwork.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class ProfileFragment extends BaseMvpViewStateFragment<ProfileMVP.ProfileView, ProfilePresenter>
        implements ProfileMVP.ProfileView {

    @BindView(R.id.profile_name)
    TextView mNameView;

    @BindView(R.id.profile_surname)
    TextView mSurnameVew;

    @BindView(R.id.profile_sex)
    TextView mSexView;

    @BindView(R.id.profile_city)
    TextView mCityView;

    @BindView(R.id.profile_about)
    TextView mAboutView;

    @BindView(R.id.profile_mobile_number)
    TextView mMobileNumbrView;

    @BindView(R.id.profile_email)
    TextView mEmailView;

    @BindView(R.id.profile_skype)
    TextView mSkypeView;

    @Inject
    ProfilePresenter mPresenter;

    private ImageView mAvatarView;
    private CollapsingToolbarLayout mCollapsingView;

    @BindView(R.id.profile_layout)
    LinearLayout mLinearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public ProfilePresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getProfileInfo();
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapse_toolbar);
        mAvatarView = (ImageView) mCollapsingView.findViewById(R.id.profile_avatar);
        mCollapsingView.setExpandedTitleColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    private void bindProfileInfo(ProfileDVO profile) {
        mCollapsingView.setTitle(profile.getFullName());
        ImageLoad.loadImage(mAvatarView, profile.getAvatar());
        mNameView.setText(profile.getName());
        mSurnameVew.setText(profile.getSurname());
        mSexView.setText(String.valueOf(profile.getSex()));
        mCityView.setText(profile.getCity());
        mAboutView.setText(profile.getAbout());
        mMobileNumbrView.setText(String.valueOf(profile.getMobile()));
        mEmailView.setText(profile.getEmail());
        mSkypeView.setText(profile.getSkype());
    }

    @Override
    public ViewState createViewState() {
        return new ProfileViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showProfileForm();
    }

    @Override
    public void showProfileForm() {
        ((ProfileViewState) viewState).setShowProfileForm();
    }

    @Override
    public void showError() {
        ((ProfileViewState) viewState).setShowError();
    }

    @Override
    public void showLoading() {
        ((ProfileViewState) viewState).setShowLoading();
//        mLinearLayout.setVisibility(View.GONE);

        /*
         setViewsEnabled(true);
        mAuthorizationButton.setProgress(Constants.ACTION_BUTTON_START);
        mErrorView.setVisibility(View.VISIBLE);
         */

    }

    @Override
    public void getProfileSuccess(ProfileDVO profile) {
//        mLinearLayout.setVisibility(View.VISIBLE);
        bindProfileInfo(profile);
    }
}
