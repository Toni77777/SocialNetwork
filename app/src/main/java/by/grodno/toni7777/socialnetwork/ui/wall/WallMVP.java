package by.grodno.toni7777.socialnetwork.ui.wall;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public final class WallMVP {

    interface Model {

        void loadPosts(int offset);

        void removePost(long postId);

        void saveInCache(WallDSO responseWall, int offset);

        void readPostsFromDB(int offset);

    }

    interface View extends MvpLceView<List<PostDVO>> {

        void removePost(PostEvent event);

        void removePostAfterDeleteServer(long removedPost);

    }

    interface Presenter extends LoadPagination {

        void removePost(long posId);

    }

    private WallMVP() {
    }
}
