package by.grodno.toni7777.socialnetwork.ui.search.persons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.ui.search.persons.adapter.PersonsAdapter;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class PersonsFragment extends BaseEventStateFragment<SwipeRefreshLayout, List<PersonDTO>, PersonsMVP.View, PersonsPresenter>
        implements PersonsMVP.View, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @BindView(R.id.persons_recycler)
    RecyclerView mPersonsRecycler;

    @BindView(R.id.progress_pagination_view)
    ProgressBar mProgressPaginView;

    @Inject
    PersonsPresenter mPersonsPresenter;

    private PersonsAdapter mPersonsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_persons, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mPersonsAdapter = new PersonsAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPersonsRecycler.setAdapter(mPersonsAdapter);
        mPersonsRecycler.setLayoutManager(linearLayoutManager);
//        mPersonsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
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
    public PersonsPresenter createPresenter() {
        return mPersonsPresenter;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO navigation
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPersonsAdapter.clear();
        presenter.loadDataWithOffset(query, true, START_LOAD);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPersonsAdapter.clear();
        presenter.loadDataWithOffset(newText, true, START_LOAD);
        return false;
    }

    @Override
    public LceViewState<List<PersonDTO>, PersonsMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<PersonDTO> getData() {
        return mPersonsAdapter.getPersons();
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return throwable.toString();
    }

    @Override
    public void setData(List<PersonDTO> data) {
        contentView.setRefreshing(false);
        mPersonsAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadDataWithOffset("", pullToRefresh, START_LOAD);
    }

    @Override
    public void onRefresh() {
        mPersonsAdapter.clear();
        loadData(true);

    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);

    }

    @Subscribe
    public void addPersonToFriend(PersonEvent event) {
        presenter.addPersonToFriend(event.getFriendId());
    }

    @Override
    public void addNewFriendSuccess(long userId) {
        Log.e("PersonFragment", "PersonFragment add new Friend with id = " + userId);
        String fullName = mPersonsAdapter.getNewFriendName(userId);
        mPersonsAdapter.updateNewFriend(userId);

        Snackbar.make(mPersonsRecycler, fullName + " add to friend ", Snackbar.LENGTH_LONG)
                .setDuration(3000)
                .show();

    }
}

