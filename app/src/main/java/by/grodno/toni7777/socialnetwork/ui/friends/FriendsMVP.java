package by.grodno.toni7777.socialnetwork.ui.friends;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;

public interface FriendsMVP {

    interface Model {

        void loadFriends(int offset);
    }

    interface View extends MvpLceView<List<FriendDVO>> {

    }

    interface Presenter extends LoadPagination {

    }
}
