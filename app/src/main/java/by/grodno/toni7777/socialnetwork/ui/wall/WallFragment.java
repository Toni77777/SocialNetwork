package by.grodno.toni7777.socialnetwork.ui.wall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.ui.newpost.NewPostActivity;
import by.grodno.toni7777.socialnetwork.ui.wall.adapter.PostAdapter;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class WallFragment extends BaseEventViewStateFragment<SwipeRefreshLayout, List<PostDTO>, WallMVP.WallView, WallPresenter>
        implements WallMVP.WallView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycler)
    RecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    View mProgressPaginView;

    private PostAdapter mPostAdapter;

    @Inject
    WallPresenter mWallPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mPostAdapter = new PostAdapter(new ArrayList<>());
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
    public LceViewState<List<PostDTO>, WallMVP.WallView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @OnClick(R.id.new_post_fab)
    void createNewPost() {
        //TODO Start activity to create new post and upload to server
    }

    @Override
    public List<PostDTO> getData() {
        return mPostAdapter.getPosts();
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return throwable.toString(); //TODO need check error for show
    }

    @Override
    public WallPresenter createPresenter() {
        return mWallPresenter;
    }

    @Override
    public void setData(List<PostDTO> data) {
        contentView.setRefreshing(false);
        mPostAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadDataWithOffset(pullToRefresh, START_LOAD);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(View.GONE);
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
        startActivity(new Intent(getContext(), NewPostActivity.class));
    }

}
