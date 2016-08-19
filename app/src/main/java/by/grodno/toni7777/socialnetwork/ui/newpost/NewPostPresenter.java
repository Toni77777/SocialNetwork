package by.grodno.toni7777.socialnetwork.ui.newpost;


import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.io.File;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.newpost.listener.UploadListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class NewPostPresenter extends MvpBasePresenter<NewPostMVP.NewPostView>
        implements BaseListener, UploadListener, MvpPresenter<NewPostMVP.NewPostView>, NewPostMVP.NewPostPresenter {

    private NewPostModel mModel;

    @Inject
    public NewPostPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new NewPostModel(socialNetworkAPI, loginPreferences, this, this);
    }

    @Override
    public void sendNewPost(String textPost, String imageURL) {
        if (imageURL == null) {
            if (isViewAttached()) {
                getView().showLoading();
            }
        }
        mModel.sendPostToServer(textPost, imageURL);
    }

    @Override
    public void sendImagePost(File file) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.uploadPostImage(file);
    }

    @Override
    public void onUploadImage(String imageURL) {
        if (isViewAttached()) {
            getView().onImagePostUploaded(imageURL);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError();
        }
    }


    @Override
    public void uploadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError();
        }
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().publishSuccess();
        }
    }
}
