package by.grodno.toni7777.socialnetwork.ui.people.persons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import by.grodno.toni7777.socialnetwork.R;

public class PersonsFragment extends Fragment implements SearchView.OnQueryTextListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_persons, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_persons, menu);
        SearchView searchPeople = (SearchView) menu.findItem(R.id.search).getActionView();
        searchPeople.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO navigation
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO request to server
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO request to server
        return false;
    }
}
