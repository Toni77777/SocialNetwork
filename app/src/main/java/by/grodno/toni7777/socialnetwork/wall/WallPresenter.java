package by.grodno.toni7777.socialnetwork.wall;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.mvp.RxModelListener;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkService;
import rx.Observable;

public class WallPresenter extends MvpBasePresenter<WallView>
        implements RxModelListener<List<PostDTO>>, MvpPresenter<WallView>, LoadPagination {

    private WallModel model = new WallModel(this);
    private boolean mPullRefresh;
    private static final int LIMIT = 4;

    @Override
    public void loadDataWithOffset(boolean pullToRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }
        mPullRefresh = pullToRefresh;
        Observable<WallDTO> observable = NetworkService.netWall().getPost(1, offset, LIMIT); // fake userId = 1
        model.loadRxData(observable);
    }

    @Override
    public void loadRxCompleted() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    @Override
    public void loadRxNext(List<PostDTO> post) {
        if (isViewAttached()) {
            getView().setData(post);
        }
    }


    @Override
    public void loadRxError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mPullRefresh);
        }
    }
}
