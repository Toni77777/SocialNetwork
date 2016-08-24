package by.grodno.toni7777.socialnetwork.ui.wall;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public interface WallMVP {

    interface WallModel {

        void loadPosts(int offset);

        void removePost(long postId);

    }

    interface WallView extends MvpLceView<List<PostDVO>> {

        void removePost(PostEvent event);

        void removePostAfterDeleteServer(long removedPost);

    }

    interface WallPresenter extends LoadPagination {

        void removePost(long posId);

    }
}
