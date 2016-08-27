package by.grodno.toni7777.socialnetwork.ui.group;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.ui.group.listener.GroupPagination;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public final class GroupMVP {

    interface Model {

        void loadPosts(long groupId, int offset);

    }

    interface View extends MvpLceView<List<PostDVO>> {

    }

    interface Presenter extends GroupPagination {

    }

    private GroupMVP() {
    }
}
