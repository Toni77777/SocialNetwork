package by.grodno.toni7777.socialnetwork.ui.wall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import by.grodno.toni7777.socialnetwork.base.EmptyRecyclerView;
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.base.event.LikeEvent;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.base.event.PostPublishSuccess;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.newpost.NewPostActivity;
import by.grodno.toni7777.socialnetwork.ui.wall.adapter.PostAdapter;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class WallFragment extends BaseEventViewStateFragment<SwipeRefreshLayout, List<PostDVO>, WallMVP.View, WallPresenter>
        implements WallMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycler)
    EmptyRecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    android.view.View mProgressPaginView;

    @BindView(R.id.empty_recycler)
    View mEmptyView;

    private PostAdapter mPostAdapter;

    @Inject
    WallPresenter mWallPresenter;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wall, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mPostAdapter = new PostAdapter(new ArrayList<>());
        mPostsRecycler.setEmptyView(mEmptyView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPostsRecycler.setAdapter(mPostAdapter);
        mPostsRecycler.setLayoutManager(linearLayoutManager);
        mPostsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }

    @Override
    public void onRefresh() {
        mPostAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<List<PostDVO>, WallMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<PostDVO> getData() {
        return mPostAdapter.getPosts();
    }

    @Override
    public WallPresenter createPresenter() {
        return mWallPresenter;
    }

    @Override
    public void setData(List<PostDVO> data) {
        contentView.setRefreshing(false);
        mPostAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mEmptyView.setVisibility(View.GONE);
        presenter.loadDataWithOffset(pullToRefresh, START_LOAD);
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

    @Override
    @Subscribe
    public void removePost(PostEvent event) {
        presenter.removePost(event.getPostId());
    }

    @Override
    public void removePostAfterDeleteServer(long removedPostId) {
        mPostAdapter.deleteRemovedPost(removedPostId);
    }

    @OnClick(R.id.new_post_fab)
    void newPost() {
        Intent postIntent = new Intent(getContext(), NewPostActivity.class);
        postIntent.putExtra(Constants.SHARE_USER_ID, new LoginPreferences(getContext()).getUserId());
        startActivity(postIntent);
    }

    @Subscribe
    public void newPostPublishSuccess(PostPublishSuccess event) {
        mPostAdapter.clear();
        loadData(true);
    }

    @Subscribe
    public void sentLike(LikeEvent event) {
        Log.e("Like", "Like " + event.getLike());
        presenter.sendLike(event);
    }

    @Override
    public void postLiked(PostDVO post) {
        mPostAdapter.updateLike(post);
    }
}
