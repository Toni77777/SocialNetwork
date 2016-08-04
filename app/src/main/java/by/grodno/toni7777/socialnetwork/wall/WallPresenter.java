package by.grodno.toni7777.socialnetwork.wall;

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
import rx.Observable;

public class WallPresenter extends MvpBasePresenter<WallView>
        implements ModelListener<List<PostDTO>>, MvpPresenter<WallView>, LoadPagination {

    private WallModel mModel = new WallModel(this);
    private boolean mPullRefresh;
    private SocialNetworkAPI mSocialNetworkAPI;

    @Inject
    public WallPresenter(SocialNetworkAPI socialNetworkAPI) {
        mSocialNetworkAPI = socialNetworkAPI;
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mPullRefresh = forceRefresh;
        Observable<WallDTO> observable = NetworkServiceTest.netWall().getPost(1, offset, LIMIT); // fake userID = 1
//        Observable<WallDTO> observable = mSocialNetworkAPI.getPost(1, offset, LIMIT); // fake userID = 1
        mModel.loadData(observable);
    }

    @Override
    public void loadCompleted() {
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
            getView().showError(e, mPullRefresh);
        }
    }
}
