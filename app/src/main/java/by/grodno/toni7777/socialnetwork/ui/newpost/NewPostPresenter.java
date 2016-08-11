package by.grodno.toni7777.socialnetwork.ui.newpost;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class NewPostPresenter extends MvpBasePresenter<NewPostMVP.NewPostView>
        implements BaseListener, MvpPresenter<NewPostMVP.NewPostView>, NewPostMVP.NewPostPresenter {

    private NewPostModel mModel;

    @Inject
    public NewPostPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new NewPostModel(socialNetworkAPI, loginPreferences, this);
    }

    @Override
    public void sendNewPost(String textPost, Long imageId) {
        mModel.sendPostToServer(textPost, imageId);
    }


    @Override
    public void onLoadCompleted() {

    }

    @Override
    public void loadError(Throwable e) {

    }
}
