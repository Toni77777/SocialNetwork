package by.grodno.toni7777.socialnetwork.wall;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;

import static by.grodno.toni7777.socialnetwork.util.Constants.LIMIT;

import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import rx.Observable;

public class WallPresenter extends MvpBasePresenter<WallView>
        implements ModelListener<List<PostDTO>>, MvpPresenter<WallView>, LoadPagination {

    private final WallModel mModel = new WallModel(this);
    private boolean mForceRefresh;
    private SocialNetworkAPI mSocialNetworkAPI;
    private LoginPreferences mLoginPreferences;

    @Inject
    public WallPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mSocialNetworkAPI = socialNetworkAPI;
        mLoginPreferences = loginPreferences;
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        Log.e("TOKEN", "Token = " + mLoginPreferences.getAccessToken());
        Log.e("TOKEN", "User id = " + mLoginPreferences.getUserId());
        Observable<WallDTO> observable = NetworkServiceTest.netWall().getPost(1, offset, LIMIT, "9ad3cccb-f0bd-4245-876b-26ece79c08fe"); // fake userID = 1
//        Observable<WallDTO> observable = mSocialNetworkAPI.getPost(1, offset, LIMIT); // fake userID = 1
        mModel.loadData(observable);
    }

    @Override
    public void onLoadCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void loadNext(List<PostDTO> post) {
        if (isViewAttached()) {
            getView().setData(post);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }
}
