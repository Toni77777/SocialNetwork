package by.grodno.toni7777.socialnetwork.people;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseEventStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.NewFriend;
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.PeopleDTO;
import by.grodno.toni7777.socialnetwork.people.adapter.PeopleAdapter;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class PeopleFragment extends BaseEventStateFragment<SwipeRefreshLayout, PeopleDTO, PeopleView, PeoplePresenter>
        implements PeopleView, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @BindView(R.id.people_recycler)
    RecyclerView mPeopleRecycler;

    @BindView(R.id.progress_pagination_view)
    RelativeLayout mProgressPaginView;

    @Inject
    PeoplePresenter mPeoplePresenter;

    private PeopleAdapter mPeopleAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_friends, menu);
        SearchView searchPeople = (SearchView) menu.findItem(R.id.search).getActionView();
        searchPeople.setOnQueryTextListener(this);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchPeople.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.send_event) {
            EventBus.getDefault().post(new NewFriend(id));
            return true;
        }

        return true;
    }

    @Override
    public PeoplePresenter createPresenter() {
        return mPeoplePresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getNetworkComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        mPeopleAdapter = new PeopleAdapter(new ArrayList<>(), new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mPeopleRecycler.setLayoutManager(linearLayoutManager);
        mPeopleRecycler.setAdapter(mPeopleAdapter);
        contentView.setOnRefreshListener(this);
//        mPeopleRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Search", "onQueryTextChange " + newText);
//        mPeopleAdapter.clear();
//        mPeopleAdapter.update(FakeFriens.createAfterSearch(5));
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Search", "onQueryTextSubmit() " + query);
        return false;
    }

    @Override
    public LceViewState<PeopleDTO, PeopleView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public PeopleDTO getData() {
        return mPeopleAdapter.getPeople();
    }

    @Override
    public void setData(PeopleDTO data) {
        contentView.setRefreshing(false);
        mPeopleAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadDataWithOffset(pullToRefresh, START_LOAD);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Subscribe
    public void addPersonToFriend(PersonEvent event) {
        EventBus.getDefault().post(new NewFriend(event.mId));
    }
}
