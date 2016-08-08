package by.grodno.toni7777.socialnetwork.people;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.PeopleDTO;
import by.grodno.toni7777.socialnetwork.test.NetworkServiceTest;
import rx.Observable;

public class PeoplePresenter extends MvpBasePresenter<PeopleView>
        implements ModelListener<PeopleDTO>, MvpPresenter<PeopleView>, LoadPagination {

    private final PeopleModel mModel = new PeopleModel(this);
    private boolean mForceRefresh;
    private SocialNetworkAPI mSocialNetworkAPI;

    @Inject
    public PeoplePresenter(SocialNetworkAPI socialNetworkAPI) {
        mSocialNetworkAPI = socialNetworkAPI;
    }

    @Override
    public void loadDataWithOffset(boolean forceRefresh, int offset) {
        if (isViewAttached()) {
            getView().showLoading(forceRefresh);
        }
        mForceRefresh = forceRefresh;
        Observable<PeopleDTO> observable = NetworkServiceTest.netFriends().getFriends(1, offset, 10); // fake userID = 1
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
    public void loadNext(PeopleDTO people) {
        if (isViewAttached()) {
            getView().setData(people);
        }
    }

    @Override
    public void loadError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, mForceRefresh);
        }
    }
}
