package by.grodno.toni7777.socialnetwork.ui.wall;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;

public interface WallMVP {

    interface WallModel {

        void loadPosts(int offset);

        void removePost(long postId);

    }

    interface WallView extends MvpLceView<List<PostDTO>> {

        void removePost(PostEvent event);

    }

    interface WallPresenter extends LoadPagination {

        void removePost(long posId);

    }
}
