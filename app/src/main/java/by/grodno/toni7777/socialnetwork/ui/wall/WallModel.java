package by.grodno.toni7777.socialnetwork.ui.wall;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
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


public class WallModel implements BaseModel, WallMVP.Model {

    private ModelListener<List<PostDVO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;
    private RemovePostListener mRemoveListener;

    public WallModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO,
                     ModelListener<List<PostDVO>> listener, RemovePostListener removeListener) {
        mNetworkAPI = socialNetworkAPI;
        mDatabaseDAO = databaseDAO;
        mPreferences = loginPreferences;
        mListener = listener;
        mRemoveListener = removeListener;
    }

    public void loadPosts(int offset) {
        Observable<WallDTO> postsObservable = mNetworkAPI.getPost(mPreferences.getUserId(), offset, SMALL_LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .map(ConverterDTOtoDSO::converteDTOtoDSO)
                .doOnNext(wallDSO -> saveInCache(wallDSO, offset))
                .compose(RxUtil.<WallDSO>applySchedulers())
                .subscribe(
                        wall -> {
                            Log.e("Wall", wall.toString());
                        },
                        throwable -> {
                            unsubscribe();
                            readPostsFromDB(offset);
                            mListener.loadError(throwable);
                            Log.e("Wall", "Error " + throwable);
                        },
                        () -> {
                            unsubscribe();
                            readPostsFromDB(offset);
                        }
                );
    }

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
        Log.e("Wall", "Read from db " + offset);
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
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
