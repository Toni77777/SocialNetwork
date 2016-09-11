package by.grodno.toni7777.socialnetwork.ui.wall;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.base.event.LikeEvent;
import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.QueryProperties;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.LikeDTO;
import by.grodno.toni7777.socialnetwork.network.model.LikeResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.wall.listener.LikeListener;
import by.grodno.toni7777.socialnetwork.ui.wall.listener.RemovePostListener;

import static by.grodno.toni7777.socialnetwork.util.Constants.SMALL_LIMIT;

import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDVO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;


public class WallModel implements BaseModel, WallMVP.Model {

    private ModelListener<List<PostDVO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;
    private RemovePostListener mRemoveListener;
    private LikeListener mLikeListener;


    public WallModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO,
                     ModelListener<List<PostDVO>> listener, RemovePostListener removeListener, LikeListener likeListener) {
        mNetworkAPI = socialNetworkAPI;
        mDatabaseDAO = databaseDAO;
        mPreferences = loginPreferences;
        mListener = listener;
        mRemoveListener = removeListener;
        mLikeListener = likeListener;
    }

    public void loadPosts(int offset) {
        Observable<WallDTO> postsObservable = mNetworkAPI.getPost(mPreferences.getUserId(), offset, SMALL_LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .map(new Func1<WallDTO, WallDTO>() {
                    @Override
                    public WallDTO call(WallDTO wallDTO) {
                        Log.e("Call", "Call form wall");
                        return wallDTO;
                    }
                })

                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                Log.e("Retry", "Error retry !!!!!!!!!!");
                                if (check(throwable)) {
                                    Log.e("Retry", "Error retry 401");
                                    return mNetworkAPI.refreshAccessToken(QueryProperties.GRAND_TYPE_REFRESH_TOKEN, QueryProperties.CLIENT_ID_VALUE, mPreferences.getRefreshToken()).flatMap(new Func1<AuthorizationDTO, Observable<?>>() {
                                        @Override
                                        public Observable<?> call(AuthorizationDTO authorizationDTO) {
                                            mPreferences.setAccessToken(authorizationDTO.getAccessToken());
                                            Log.e("Retry", "Token " + authorizationDTO.getAccessToken());
                                            return postsObservable;
                                        }
                                    });
//                                    return Observable.just(postsObservable);
                                }

                                return Observable.error(throwable);
                            }
                        });
                    }
                })
//                .onErrorResumeNext(refreshTokenAndRetry(postsObservable))
                .map(ConverterDTOtoDSO::converteDTOtoDSO)
                .doOnNext(wallDSO -> saveInCache(wallDSO, offset))
                .compose(RxUtil.<WallDSO>applySchedulers())
                .subscribe(
                        wall -> {
                            Log.e("Wall", wall.toString());
                        },
                        throwable -> {
                            unsubscribe();
//                            if (throwable.getMessage().equals("HTTP 401 Unauthorized")) {
//                                Log.e("Error", "My Error 401 !!!!!!!!!");
//                                refreshAccessToken();
//                            }
                            readPostsFromDB(offset);
                            mListener.loadError(throwable);
                            Log.e("Wall", "Error " + throwable.getMessage());
                        },
                        () -> {
                            unsubscribe();
                            readPostsFromDB(offset);
                        }
                );
    }

//
//    http://stackoverflow.com/questions/30517761/implement-retrywhen-logic
//    http://stackoverflow.com/questions/26201420/retrofit-with-rxjava-handling-network-exceptions-globally

    //   nedd !!!!!
//    private <T> Func1<Throwable, ? extends Observable<? extends T>> refreshTokenAndRetry(final Observable<T> toBeResumed) {
//        return new Func1<Throwable, Observable<? extends T>>() {
//            @Override
//            public Observable<? extends T> call(Throwable throwable) {
//                Log.e("RxJava", "RxJava !!!!!!!!!!! Call");
//                if (check(throwable)) {
//                    return mNetworkAPI.refreshAccessToken(QueryProperties.GRAND_TYPE_REFRESH_TOKEN, QueryProperties.CLIENT_ID_VALUE, mPreferences.getRefreshToken()).flatMap(new Func1<AuthorizationDTO, Observable<? extends T>>() {
//                        @Override
//                        public Observable<? extends T> call(AuthorizationDTO authorizationDTO) {
//                            mPreferences.setAccessToken(authorizationDTO.getAccessToken());
//                            Log.e("RxJava", "RxJava Refresh token = " + authorizationDTO.getRefreshToken());
////                            return toBeResumed.retry();
////                            return toBeResumed.retry(); do you need retry?
//                            return toBeResumed;
//                        }
//                    });
//                }
//                return Observable.error(throwable);
//            }
//        };
//    }


//    }

    private boolean check(Throwable throwable) {
        return throwable.getMessage().equals("HTTP 401 Unauthorized");
    }
//
//    private void refreshAccessToken() {
//        Observable<AuthorizationDTO> tokenObservable = mNetworkAPI.refreshAccessToken(QueryProperties.GRAND_TYPE_REFRESH_TOKEN, QueryProperties.CLIENT_ID_VALUE, mPreferences.getRefreshToken());
//
//        mSubscription = tokenObservable
//                .compose(RxUtil.<AuthorizationDTO>applySchedulers())
//                .subscribe(
//                        authorization -> {
//                            mPreferences.setAccessToken(authorization.getAccessToken());
//                            mPreferences.setRefreshToken(authorization.getRefreshToken());
//                            Log.e("Refresh", authorization.getAccessToken().toString());
//
//                        },
//                        throwable -> {
//                            unsubscribe();
//                            mListener.loadError(throwable);
//                            Log.e("Logn Error", "Login Error" + throwable.toString());
//                        },
//                        () -> {
//                            unsubscribe();
//                        });
//    }

    @Override
    public void saveInCache(WallDSO responseWall, int offset) {
        RealmResults<WallDSO> walls = mDatabaseDAO.readAll(Realm.getDefaultInstance(), WallDSO.class);
        if (walls.size() == 0) { // have not wall object from DB
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseWall);
        } else if (offset == 0) { // if refresh to new post
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseWall);
        } else {
            RealmList<PostDSO> responsePost = responseWall.getPostDSO();
            mDatabaseDAO.updateWall(Realm.getDefaultInstance(), responsePost);
        }
    }

    @Override
    public void readPostsFromDB(int offset) {
        WallDSO wallDSO = mDatabaseDAO.findFirst(Realm.getDefaultInstance(), WallDSO.class);

        if (wallDSO == null) {
            throw new IllegalArgumentException("WallDSO null");
        } else {
            RealmList<PostDSO> posts = wallDSO.getPostDSO();
            if (posts == null) {
                throw new IllegalArgumentException("Posts from database null");
            }
            List<PostDVO> readData = ConverterDTOtoDVO.converteDSOtoDVO(posts);
            List<PostDVO> result = new ArrayList<>();
            int size = readData.size();
            if (size >= (offset + SMALL_LIMIT - 1)) {
                result = readData.subList(offset, offset + SMALL_LIMIT - 1);
            } else {
                result = readData.subList(offset, size);
            }
            mListener.loadNext(result);
            mListener.onLoadCompleted();
        }
    }

    @Override
    public void removePost(long postId) {
        Observable<ResponseDTO> postsObservable = mNetworkAPI.removePost(postId, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mRemoveListener.onRemoveCompleted(postId);
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mRemoveListener.removeGetError(throwable);
                        },
                        () -> {
                            unsubscribe();
                        }
                );
    }

    @Override
    public void sendLike(LikeEvent event) {
        LikeDTO like = new LikeDTO(event.getLike());
        Observable<LikeResponseDTO> likeObservable = mNetworkAPI.likeUserPost(event.getPost().getPostId(),
                like, mPreferences.getAccessToken());

        mSubscription = likeObservable
                .compose(RxUtil.<LikeResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
//                            Log.e("Like", "like response" + response);
                            PostDVO post = event.getPost();
//                            Integer isLikePost = post.getIsLike();
//                            if (isLikePost == null) {
//                                isLikePost = 0;
//                            }
//                            Integer isLikeEvent = event.getLike();
//                            Integer isLikeResponse = response.getIsLike();
//                            Log.e("Like", "like response response  " + isLikeResponse);
//                            //Correct work
//                            if ((isLikePost.equals(0)) && (isLikeEvent.equals(1)) && (isLikeEvent.equals(isLikeResponse))) {
//                                Log.e("Like", "Like work !!!");
//                                Integer countLike = post.getLike();
//                                Log.e("Like", "Count like  " + countLike);
//
//                                post.setLike(countLike + 1);
//                                Log.e("Like", "Post like  " + post.getLike());
//                                post.setIsLike(isLikeResponse);
//                            }
//                            post.setIsLike(response.getIsLike()); // fail

//                            if (isLike.equals(isLikeResponse) && isLikeResponse.equals(Constants.POST_LIKE)) {
//                                post.setLike(post.getLike() - 1);
//                            } else if (isLike.equals(isLikeResponse) && isLikeResponse.equals(Constants.POST_DISLIKE)) {
//                                post.setDislike(post.getDislike() - 1);
//                            } else if (isLike.equals(0) && (isLikeResponse.equals(1))) {
//                                post.setLike(post.getLike() + 1);
//                            } else if (isLike.equals(0) && (isLikeResponse.equals(-1))) {
//                                post.setDislike(post.getDislike() + 1);
//                            }

//                            if ((isLike.equals(0)) && (isLike.equals(1)) && (response.getIsLike().equals(isLike))) {
//                                Log.e("Like", "Like work !!!");
//                                post.setLike(post.getLike() + 1);
//                            }
                            mLikeListener.sendLikeCompleted(post);
                        },
                        throwable -> {
                            unsubscribe();
                            mLikeListener.sendLikeError(throwable);
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
