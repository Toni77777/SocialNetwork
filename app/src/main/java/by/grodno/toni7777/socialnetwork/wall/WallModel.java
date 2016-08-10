package by.grodno.toni7777.socialnetwork.wall;

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

public class WallModel implements BaseModel {

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
        Observable<WallDTO> postsObservable = NetworkServiceTest.netWall().getPost(mPreferences.getUserId(), offset, LIMIT, mPreferences.getAccessToken());
//        Observable<WallDTO> observable = mSocialNetworkAPI.getPost(1, offset, LIMIT);

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
        Log.e("Post", "Model " + postId);
        Observable<PostRemoveDTO> postsObservable = NetworkServiceTest.netWall().removePost(postId, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<PostRemoveDTO>applySchedulers())
                .subscribe(
                        post -> {
                            Log.e("Remove", post.toString());
                        },
                        throwable -> {
                            unsubscribe();
                            Log.e("Remove", throwable.toString());
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
