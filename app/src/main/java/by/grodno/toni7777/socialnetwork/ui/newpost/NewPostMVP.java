package by.grodno.toni7777.socialnetwork.ui.newpost;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface NewPostMVP {

    interface NewPostModel {

        void sendPostToServer(String textPost, Long imageId);

    }

    interface NewPostView extends MvpView {

        void showPostForm();

        void showError();

        void showLoading();

        void sendSuccess();


    }

    interface NewPostPresenter {

        void sendNewPost(String textPost, Long imageId);
    }
}
