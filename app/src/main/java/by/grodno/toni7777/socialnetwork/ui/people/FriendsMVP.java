package by.grodno.toni7777.socialnetwork.ui.people;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;

public interface FriendsMVP {

    interface FriendsModel {

        void loadFriends(int offset);
    }

    interface FriendsView extends MvpLceView<List<FriendDTO>> {

    }

    interface FriendsPresenter extends LoadPagination {

    }
}
