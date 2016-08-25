package by.grodno.toni7777.socialnetwork.ui.newpost;

import android.util.Log;

import java.io.File;

import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ImageResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.NewPostDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.newpost.listener.UploadListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;

public class NewPostModel implements BaseModel, NewPostMVP.Model {

    private BaseListener mListener;
    private UploadListener mUploadListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public NewPostModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                        BaseListener listener, UploadListener uploadListener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
        mUploadListener = uploadListener;
    }

    @Override
    public void sendPostToServer(String textPost, String imageURL) {
        Log.e("Start", "Sent post to server");
        unsubscribe();
        NewPostDTO newPost = new NewPostDTO(mPreferences.getUserId(), imageURL, textPost);
        Observable<ResponseDTO> tokenObservable = mNetworkAPI.sendNewPost(newPost, mPreferences.getAccessToken());
        mSubscription = tokenObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
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
    public void uploadPostImage(File file) {
        Log.e("Response", "StartLoad Image Id");
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Observable<ImageResponseDTO> tokenObservable = mNetworkAPI.uploadImageToServer(body, name, mPreferences.getAccessToken());
        mSubscription = tokenObservable
                .compose(RxUtil.<ImageResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            mUploadListener.onUploadImage(response.getImageURL());
                        },
                        throwable -> {
                            unsubscribe();
                            mUploadListener.uploadError(throwable);
                            mListener.loadError(throwable);
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
