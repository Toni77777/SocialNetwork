package by.grodno.toni7777.socialnetwork.ui.friend;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.ui.group.listener.GroupPagination;
import by.grodno.toni7777.socialnetwork.ui.model.FriendStateDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public final class FriendMVP {

    interface Model {

        void loadPosts(long friendId, int offset);

        void loadUserInfo(long friendId);

        void removeUserFromFriends(long friendId);

    }

    interface View extends MvpLceView<FriendStateDVO> {

        void profileLoaded(ProfileDVO profile);

        void postLoaded(List<PostDVO> posts);

        void friendRemoved();

    }

    interface Presenter extends GroupPagination {

        void getProfileInfo(long userId, boolean forceRefresh);

        void removeUserFromFriends(long friendId);

    }

    private FriendMVP() {
    }
}
