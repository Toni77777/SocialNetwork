package by.grodno.toni7777.socialnetwork.ui.friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseEventViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.ui.friend.adapter.FriendAdapter;
import by.grodno.toni7777.socialnetwork.ui.group.listener.PaginationGroupListener;
import by.grodno.toni7777.socialnetwork.ui.model.FriendStateDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.ui.newpost.NewPostActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;

public class FriendFragment extends BaseEventViewStateFragment<SwipeRefreshLayout, FriendStateDVO, FriendMVP.View, FriendPresenter>
        implements FriendMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycler)
    RecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    View mProgressPaginView;

    private FriendAdapter mFriendAdapter;

    @Inject
    FriendPresenter mFriendPresenter;

    private long mFriendId;
    private static final String STATE_FRIEND_ID = "stateFriendId";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(Constants.SHARE_FRIEND_ID)) {
                mFriendId = bundle.getLong(Constants.SHARE_FRIEND_ID);
                Log.e("Friend", "Friend id = " + mFriendId);
            }
        }
        if (savedInstanceState != null) {
            mFriendId = savedInstanceState.getLong(STATE_FRIEND_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mFriendAdapter = new FriendAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPostsRecycler.setAdapter(mFriendAdapter);
        mPostsRecycler.setLayoutManager(linearLayoutManager);
        mPostsRecycler.addOnScrollListener(new PaginationGroupListener(linearLayoutManager, mProgressPaginView, mFriendPresenter, mFriendId));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(STATE_FRIEND_ID, mFriendId);
    }

    @Override
    public void onRefresh() {
        mFriendAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<FriendStateDVO, FriendMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public FriendStateDVO getData() {
        return new FriendStateDVO(mFriendAdapter.getProfile(), mFriendAdapter.getPosts());
    }

    @Override
    public FriendPresenter createPresenter() {
        return mFriendPresenter;
    }

    @Override
    public void setData(FriendStateDVO saveState) {
        mFriendAdapter.setProfile(saveState.getProfile());
        mFriendAdapter.update(saveState.getPosts());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getProfileInfo(mFriendId, pullToRefresh);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(Throwable throwable, boolean pullToRefresh) {
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(android.view.View.GONE);
        Snackbar.make(mPostsRecycler, ErrorHanding.getErrorMessage(throwable, getContext()), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Subscribe
    public void removePost(PostEvent event) {
//        presenter.removePost(event.getPostId());
    }

    @OnClick(R.id.new_post_fab)
    void newPost() {
        startActivity(new Intent(getContext(), NewPostActivity.class));
    }

    @Override
    public void profileLoaded(ProfileDVO profile) {
        mFriendAdapter.setProfile(profile);
    }

    @Override
    public void postLoaded(List<PostDVO> posts) {
        contentView.setRefreshing(false);
        mFriendAdapter.update(posts);
    }
}
