package by.grodno.toni7777.socialnetwork.ui.friend;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.ui.group.listener.GroupPagination;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public final class FriendMVP {

    interface Model {

        void loadPosts(long friendId, int offset);

        void loadUserInfo(long friendId);

    }

    interface View extends MvpLceView<FriendSaveState> {

        void profileLoaded(ProfileDVO profile);

        void postLoaded(List<PostDVO> posts);

    }

    interface Presenter extends GroupPagination {

        void getProfileInfo(long userId, boolean forceRefresh);

    }

    private FriendMVP() {
    }
}
