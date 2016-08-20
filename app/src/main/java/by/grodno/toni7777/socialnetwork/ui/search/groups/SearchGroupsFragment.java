package by.grodno.toni7777.socialnetwork.ui.search.groups;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import by.grodno.toni7777.socialnetwork.base.event.SearchGroupEvent;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.ui.search.groups.adapter.SearchGroupsAdapter;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class SearchGroupsFragment extends BaseEventStateFragment<SwipeRefreshLayout, List<GroupDTO>, SearchGroupsMVP.View, SearchGroupsPresenter>
        implements SearchGroupsMVP.View, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @BindView(R.id.search_groups_recycler)
    RecyclerView mSearchGroupsRecycler;

    @BindView(R.id.progress_pagination_view)
    ProgressBar mProgressPaginView;

    @Inject
    SearchGroupsPresenter mSearchGroupsPresenter;

    private SearchGroupsAdapter mSearchGroupsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_groups_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mSearchGroupsAdapter = new SearchGroupsAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mSearchGroupsRecycler.setAdapter(mSearchGroupsAdapter);
        mSearchGroupsRecycler.setLayoutManager(linearLayoutManager);
//        mSearchGroupsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_persons, menu);
        SearchView searchPeople = (SearchView) menu.findItem(R.id.persons_search).getActionView();
        searchPeople.onActionViewExpanded();
        searchPeople.setOnQueryTextListener(this);
        searchPeople.setIconified(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public SearchGroupsPresenter createPresenter() {
        return mSearchGroupsPresenter;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO navigation
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchGroupsAdapter.clear();
        presenter.loadDataWithOffset(query, true, START_LOAD);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mSearchGroupsAdapter.clear();
        presenter.loadDataWithOffset(newText, true, START_LOAD);
        return false;
    }

    @Override
    public LceViewState<List<GroupDTO>, SearchGroupsMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<GroupDTO> getData() {
        return mSearchGroupsAdapter.getGroups();
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return throwable.toString();
    }

    @Override
    public void setData(List<GroupDTO> data) {
        contentView.setRefreshing(false);
        mSearchGroupsAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
//        presenter.loadDataWithOffset(" ", pullToRefresh, START_LOAD);
    }

    @Override
    public void onRefresh() {
        mSearchGroupsAdapter.clear();
        presenter.loadDataWithOffset("", true, START_LOAD);

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

    @Subscribe
    public void addGroupToFavorite(SearchGroupEvent event) {
        // TODO open friend wall
    }
}
