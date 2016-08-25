package by.grodno.toni7777.socialnetwork.ui.search.persons;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;

public final class PersonsMVP {

    interface Model {

        void findPersons(String fullNameSearch, int offset);

        void addPersonToFriend(Long userId);
    }

    interface View extends MvpLceView<List<PersonDTO>> {

        void addPersonToFriend(PersonEvent event);

        void addNewFriendSuccess(long userId);
    }

    interface Presenter {

        void loadDataWithOffset(String fullNameSearch, boolean forceRefresh, int offset);

        void addPersonToFriend(Long userId);

    }

    private PersonsMVP() {
    }
}
