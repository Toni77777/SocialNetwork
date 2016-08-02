package by.grodno.toni7777.socialnetwork.wall;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkService;
import rx.Observable;

public class WallPresenter extends MvpBasePresenter<WallView>
        implements ModelListener<List<PostDTO>>, MvpPresenter<WallView>, LoadPagination {

    private WallModel model = new WallModel(this);
    private boolean mPullRefresh;
    private static final int LIMIT = 4;

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mPullRefresh = forceRefresh;
        Observable<WallDTO> observable = NetworkService.netWall().getPost(1, offset, LIMIT); // fake userId = 1
        model.loadData(observable);
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
