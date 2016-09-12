package by.grodno.toni7777.socialnetwork.ui.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseEventViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.GroupRemovedEvent;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.ui.group.adapter.GroupAdapter;
import by.grodno.toni7777.socialnetwork.ui.group.listener.PaginationGroupListener;
import by.grodno.toni7777.socialnetwork.ui.model.GroupInfoDVO;
import by.grodno.toni7777.socialnetwork.ui.model.GroupStateDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.newpost.NewPostActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;

public class GroupFragment extends BaseEventViewStateFragment<SwipeRefreshLayout, GroupStateDVO, GroupMVP.View, GroupPresenter>
        implements GroupMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.posts_recycler)
    RecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    View mProgressPaginView;

    private GroupAdapter mGroupAdapter;

    @Inject
    GroupPresenter mWallPresenter;

    private long mGroupId;
    private static final String STATE_GROUP_ID = "stateGroupId";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(Constants.SHARE_GROUP_ID)) {
                mGroupId = bundle.getLong(Constants.SHARE_GROUP_ID);
                Log.e("Group", "Group id = " + mGroupId);
            }
        }
        if (savedInstanceState != null) {
            mGroupId = savedInstanceState.getLong(STATE_GROUP_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_group, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.group_remove_item) {
            presenter.removeGroupFromFavorite(mGroupId);
            return true;
        }
        return true;
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mGroupAdapter = new GroupAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPostsRecycler.setAdapter(mGroupAdapter);
        mPostsRecycler.setLayoutManager(linearLayoutManager);
        mPostsRecycler.addOnScrollListener(new PaginationGroupListener(linearLayoutManager, mProgressPaginView, mWallPresenter, mGroupId));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(STATE_GROUP_ID, mGroupId);
    }

    @Override
    public void onRefresh() {
        mGroupAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<GroupStateDVO, GroupMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public GroupStateDVO getData() {
        return new GroupStateDVO(mGroupAdapter.getInfo(), mGroupAdapter.getPosts());
    }

    @Override
    public void setData(GroupStateDVO groupState) {
        mGroupAdapter.setInfo(groupState.getGroupInfo());
        mGroupAdapter.update(groupState.getPosts());
    }

    @Override
    public GroupPresenter createPresenter() {
        return mWallPresenter;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadGroupInfo(mGroupId, pullToRefresh);
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
    public void infoLoaded(GroupInfoDVO info) {
        mGroupAdapter.setInfo(info);
    }

    @Override
    public void postsLoaded(List<PostDVO> posts) {
        contentView.setRefreshing(false);
        mGroupAdapter.update(posts);
    }

    @Override
    public void groupRemoved() {
        EventBus.getDefault().post(new GroupRemovedEvent());
        getActivity().finish();
    }
}
