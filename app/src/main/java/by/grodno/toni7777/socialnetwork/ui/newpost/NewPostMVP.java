package by.grodno.toni7777.socialnetwork.ui.newpost;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.io.File;

public final class NewPostMVP {

    interface Model {

        void sendPostToServer(String textPost, String imageURL);

        void uploadPostImage(File file);

    }

    interface View extends MvpView {

        void showPostForm();

        void showError();

        void showLoading();

        void onImagePostUploaded(String imageURL);

        void publishSuccess();

    }

    interface Presenter {

        void sendImagePost(File file);

        void sendNewPost(String textPost, String imageURL);
    }

    private NewPostMVP() {
    }
}
