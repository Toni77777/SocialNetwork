package by.grodno.toni7777.socialnetwork.ui.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseEventStateFragment;
import by.grodno.toni7777.socialnetwork.base.EmptyRecyclerView;
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.base.event.GroupEvent;
import by.grodno.toni7777.socialnetwork.ui.group.GroupActivity;
import by.grodno.toni7777.socialnetwork.ui.groups.adapter.GroupsAdapter;
import by.grodno.toni7777.socialnetwork.ui.model.GroupDVO;
import by.grodno.toni7777.socialnetwork.ui.search.groups.SearchGroupsActivity;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;

public class GroupsFragment extends BaseEventStateFragment<SwipeRefreshLayout, List<GroupDVO>, GroupsMVP.View, GroupsPresenter>
        implements GroupsMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.groups_recycler)
    EmptyRecyclerView mGroupsRecycler;

    @BindView(R.id.progress_pagination_view)
    ProgressBar mProgressPaginView;

    @BindView(R.id.empty_recycler)
    View mEmptyView;

    @Inject
    GroupsPresenter mGroupsPresenter;

    private GroupsAdapter mGroupsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mGroupsAdapter = new GroupsAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mGroupsRecycler.setEmptyView(mEmptyView);
        mGroupsRecycler.setAdapter(mGroupsAdapter);
        mGroupsRecycler.setLayoutManager(linearLayoutManager);
        mGroupsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_groups, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.groups_search_item) {
            getActivity().startActivity(new Intent(getContext(), SearchGroupsActivity.class));
            return true;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        mGroupsAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<List<GroupDVO>, GroupsMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<GroupDVO> getData() {
        return mGroupsAdapter.getGroups();
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return throwable.toString();
    }

    @Override
    public GroupsPresenter createPresenter() {
        return mGroupsPresenter;
    }

    @Override
    public void setData(List<GroupDVO> data) {
        contentView.setRefreshing(false);
        mGroupsAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mEmptyView.setVisibility(View.GONE);
        presenter.loadDataWithOffset(pullToRefresh, Constants.START_LOAD);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable, boolean pullToRefresh) {
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(View.GONE);
        Snackbar.make(mGroupsRecycler, ErrorHanding.getErrorMessage(throwable, getContext()), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Subscribe
    public void openGroup(GroupEvent event) {
        Intent groupIntent = new Intent(getContext(), GroupActivity.class);
        groupIntent.putExtra(Constants.SHARE_GROUP_ID, event.getGroupId());
        startActivity(groupIntent);
    }
}
