package by.grodno.toni7777.socialnetwork.ui.newpost;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.NewPostDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostResponseDTO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class NewPostModel implements BaseModel, NewPostMVP.NewPostModel {

    private BaseListener mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public NewPostModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, BaseListener listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        this.mListener = listener;
    }

    @Override
    public void sendPostToServer(String textPost, Long imageId) {
        NewPostDTO newPost = new NewPostDTO(mPreferences.getUserId(), imageId, textPost);
        Observable<PostResponseDTO> tokenObservable = mNetworkAPI.sendNewPost(newPost, mPreferences.getAccessToken());
        mSubscription = tokenObservable
                .compose(RxUtil.<PostResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mListener.onLoadCompleted();
                            }

                            Log.e("Post", "Success" + response.toString());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                            Log.e("Post", "New post error" + throwable.toString());
                        },
                        () -> {
                            unsubscribe();
                        });

    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
