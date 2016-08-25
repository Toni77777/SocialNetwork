package by.grodno.toni7777.socialnetwork.ui.friends;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.database.model.FriendsDSO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;

public final class FriendsMVP {

    interface Model {

        void loadFriends(int offset);

        void saveInCache(FriendsDSO responseWall, int offset);

        void readFriendsFromDB(int offset);
    }

    interface View extends MvpLceView<List<FriendDVO>> {

    }

    interface Presenter extends LoadPagination {

    }

    private FriendsMVP() {
    }
}
