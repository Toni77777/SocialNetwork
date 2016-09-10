package by.grodno.toni7777.socialnetwork.ui.group;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.ui.group.listener.GroupPagination;
import by.grodno.toni7777.socialnetwork.ui.model.GroupInfoDVO;
import by.grodno.toni7777.socialnetwork.ui.model.GroupStateDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public final class GroupMVP {

    interface Model {

        void loadPosts(long groupId, int offset);

        void loadGroupInfo(long groupId);

    }

    interface View extends MvpLceView<GroupStateDVO> {

        void infoLoaded(GroupInfoDVO info);

        void postsLoaded(List<PostDVO> posts);

    }

    interface Presenter extends GroupPagination {

        void loadGroupInfo(long groupId, boolean forceRefresh);

    }

    private GroupMVP() {
    }
}
