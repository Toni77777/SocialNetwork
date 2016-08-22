package by.grodno.toni7777.socialnetwork.ui.search.groups;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.network.model.GroupsDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.search.groups.listener.FavoriteListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class SearchGroupsModel implements BaseModel, SearchGroupsMVP.Model {

    private ModelListener<List<GroupDTO>> mListener;
    private FavoriteListener mFavoriteListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    protected SocialNetworkAPI mNetworkAPI;

    public SearchGroupsModel(ModelListener<List<GroupDTO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, FavoriteListener favoriteListener) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
        mFavoriteListener = favoriteListener;
    }

    @Override
    public void findGroups(String nameGroup, int offset) {
        Observable<GroupsDTO> postsObservable = mNetworkAPI.findGroups(nameGroup, offset, 10, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<GroupsDTO>applySchedulers())
                .subscribe(
                        groups -> {
                            mListener.loadNext(groups.getGroups());
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

    @Override
    public void addGroupToFavorite(Long groupId) {
        Observable<ResponseDTO> postsObservable = mNetworkAPI.addGroupToFavorite(groupId, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = postsObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mFavoriteListener.addGroupToFavoriteCompleted(groupId);
                            }
                        },
                        throwable -> {
                            unsubscribe();
                            mFavoriteListener.addGroupToFavoriteError(throwable);
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
