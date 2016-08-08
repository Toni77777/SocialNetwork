package by.grodno.toni7777.socialnetwork.people;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseEventFragment;
import by.grodno.toni7777.socialnetwork.base.event.NewFriend;
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.people.adapter.PeopleAdapter;
import by.grodno.toni7777.socialnetwork.people.fake.FakeFriens;

public class PeopleFragment extends BaseEventFragment implements SearchView.OnQueryTextListener {

    @BindView(R.id.friends_recycler)
    RecyclerView mPostsRecycler;

    @BindView(R.id.progress_pagination_view)
    RelativeLayout mProgressPaginView;

    private PeopleAdapter mFriendAdapter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFriendAdapter = new PeopleAdapter(FakeFriens.createFriends(5));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mPostsRecycler.setAdapter(mFriendAdapter);
        mPostsRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Search", "onQueryTextChange " + newText);
        mFriendAdapter.clear();
        mFriendAdapter.update(FakeFriens.createAfterSearch(5));
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Search", "onQueryTextSubmit() " + query);
        return false;
    }

    @Subscribe
    public void addPersonToFriend(PersonEvent event) {
        EventBus.getDefault().post(new NewFriend(event.mId));
    }

}
