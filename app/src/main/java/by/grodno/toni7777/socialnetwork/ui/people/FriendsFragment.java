package by.grodno.toni7777.socialnetwork.ui.people;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.base.event.FriendEvent;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.ui.people.adapter.FriendsAdapter;
import by.grodno.toni7777.socialnetwork.ui.people.persons.PersonsFragment;

import static by.grodno.toni7777.socialnetwork.util.Constants.START_LOAD;

public class FriendsFragment extends BaseEventStateFragment<SwipeRefreshLayout, List<FriendDVO>, FriendsMVP.FriendsView, FriendsPresenter>
        implements FriendsMVP.FriendsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.friends_recycler)
    RecyclerView mFreindsRecycler;

    @BindView(R.id.progress_pagination_view)
    ProgressBar mProgressPaginView;

    @Inject
    FriendsPresenter mFriendsPresenter;

    private FriendsAdapter mFriendsAdapter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mFriendsAdapter = new FriendsAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFreindsRecycler.setAdapter(mFriendsAdapter);
        mFreindsRecycler.setLayoutManager(linearLayoutManager);
        mFreindsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_friends, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.persons_search_item) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new PersonsFragment())
                    .commit();
            return true;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        mFriendsAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<List<FriendDVO>, FriendsMVP.FriendsView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<FriendDVO> getData() {
        return mFriendsAdapter.getFriends();
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return throwable.toString();
    }

    @Override
    public FriendsPresenter createPresenter() {
        return mFriendsPresenter;
    }

    @Override
    public void setData(List<FriendDVO> data) {
        contentView.setRefreshing(false);
        mFriendsAdapter.update(data);
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

    @Subscribe
    public void openFriendWall(FriendEvent event) {
        // TODO open friend wall
    }
}
