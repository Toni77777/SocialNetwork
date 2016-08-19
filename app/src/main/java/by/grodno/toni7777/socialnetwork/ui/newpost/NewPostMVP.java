package by.grodno.toni7777.socialnetwork.ui.newpost;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.io.File;

public interface NewPostMVP {

    interface NewPostModel {

        void sendPostToServer(String textPost, String imageURL);

        void uploadPostImage(File file);

    }

    interface NewPostView extends MvpView {

        void showPostForm();

        void showError();

        void showLoading();

        void onImagePostUploaded(String imageURL);

        void publishSuccess();

    }

    interface NewPostPresenter {

        void sendImagePost(File file);

        void sendNewPost(String textPost, String imageURL);
    }
}
