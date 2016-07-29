package by.grodno.toni7777.socialnetwork.wall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.wall.adapter.PostAdapter;

public class WallFragment extends BaseViewStateFragment<SwipeRefreshLayout, List<PostDTO>, WallView, WallPresenter>
        implements WallView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycler)
    RecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    RelativeLayout mProgressPaginView;

    private PostAdapter mPostAdapter;
    private static final int START_LOAD = 0;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        loadData(true);
    }

    @Override
    public LceViewState<List<PostDTO>, WallView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<PostDTO> getData() {
        return mPostAdapter.getPosts();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "Error"; // need check error for show
    }

    @Override
    public WallPresenter createPresenter() {
        return new WallPresenter();
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
}
