package by.grodno.toni7777.socialnetwork.ui.wall;

import android.util.Log;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostRemoveDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

import static by.grodno.toni7777.socialnetwork.util.Constants.LIMIT;

public class WallModel implements BaseModel, WallMVP.WallModel {

    private ModelListener<List<PostDTO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public WallModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, ModelListener<List<PostDTO>> listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        this.mListener = listener;
    }

    public void loadPosts(int offset) {
        Observable<WallDTO> postsObservable = mNetworkAPI.getPost(mPreferences.getUserId(), offset, LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .doOnNext(this::saveInCache)
                .compose(RxUtil.<WallDTO>applySchedulers())
                .subscribe(
                        wallDTO -> {
                            mListener.loadNext(wallDTO.getPosts());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            mListener.onLoadCompleted();
                        }
                );
    }


    private void saveInCache(WallDTO wallDTO) {
        // TODO Put in cache to do next step
    }

    public void removePost(long postId) {
        Observable<PostRemoveDTO> postsObservable = mNetworkAPI.removePost(postId, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<PostRemoveDTO>applySchedulers())
                .subscribe(
                        post -> {
                        },
                        throwable -> {
                            unsubscribe();
                        },
                        () -> {
                            unsubscribe();
                        }
                );
    }


    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
