package by.grodno.toni7777.socialnetwork.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class ProfileFragment extends BaseMvpViewStateFragment<ProfileMVP.View, ProfilePresenter>
        implements ProfileMVP.View {

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
    TextView mMobileNumberView;

    @BindView(R.id.profile_email)
    TextView mEmailView;

    @BindView(R.id.profile_skype)
    TextView mSkypeView;

    @BindView(R.id.error)
    TextView mErrorView;

    private ProgressBar mLoadView;
    private android.view.View mProfileView;

    @Inject
    ProfilePresenter mPresenter;

    private ImageView mAvatarView;
    private CollapsingToolbarLayout mCollapsingView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadView = (ProgressBar) getActivity().findViewById(R.id.progress);
        mProfileView = (android.view.View) getActivity().findViewById(R.id.main_content);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public ProfilePresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getProfileInfo();
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapse_toolbar);
        mAvatarView = (ImageView) mCollapsingView.findViewById(R.id.profile_avatar);
        mCollapsingView.setExpandedTitleColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.primary));
    }

    private void bindProfileInfo(ProfileDVO profile) {
        mCollapsingView.setTitle(profile.getFullName());
        ImageLoad.loadImage(mAvatarView, profile.getAvatar());
        mNameView.setText(profile.getName());
        mSurnameVew.setText(profile.getSurname());
        mSexView.setText(String.valueOf(profile.getSex()));
        mCityView.setText(profile.getCity());
        mAboutView.setText(profile.getAbout());
        mMobileNumberView.setText(String.valueOf(profile.getMobile()));
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
        mProfileView.setVisibility(android.view.View.GONE);
        mErrorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showLoading() {
        ((ProfileViewState) viewState).setShowLoading();
        mProfileView.setVisibility(android.view.View.GONE);
        mLoadView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void getProfileSuccess(ProfileDVO profile) {
        mLoadView.setVisibility(android.view.View.GONE);
        mProfileView.setVisibility(android.view.View.VISIBLE);
        bindProfileInfo(profile);
    }
}
