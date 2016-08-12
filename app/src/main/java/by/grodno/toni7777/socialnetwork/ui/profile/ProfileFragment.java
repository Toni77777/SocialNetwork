package by.grodno.toni7777.socialnetwork.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class ProfileFragment extends Fragment {

    private ImageView mAvatarView;
    private CollapsingToolbarLayout mCollapsingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapse_toolbar);
        mAvatarView = ButterKnife.findById(mCollapsingView, R.id.profile_avatar);

        mCollapsingView.setExpandedTitleColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.toolbar_title));
        mCollapsingView.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        bindProfileInfo();

    }

    private void bindProfileInfo() {
        mCollapsingView.setTitle("My Profile");
        ImageLoad.loadImage(mAvatarView, "http://maximum-hr.ru/img/164.jpg");
    }

    /*
     CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        mImageView = (ImageView) collapsingToolbarLayout.findViewById(R.id.bgheader);

        collapsingToolbarLayout.setTitle("My title");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorTitle));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorTitle));
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));

        Glide.with(mImageView.getContext())
                .load(url)
                .into(mImageView);
     */


}
