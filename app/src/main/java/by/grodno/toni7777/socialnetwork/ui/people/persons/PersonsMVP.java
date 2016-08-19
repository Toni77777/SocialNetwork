package by.grodno.toni7777.socialnetwork.ui.people.persons;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;

public interface PersonsMVP {

    interface PersonsModel {

        void findPersons(String fullNameSearch, int offset);
    }

    interface PersonsView extends MvpLceView<List<PersonDTO>> {

    }

    interface PersonsPresenter {

        void loadDataWithOffset(String fullNameSearch, boolean forceRefresh, int offset);

    }
}
