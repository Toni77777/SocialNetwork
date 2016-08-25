package by.grodno.toni7777.socialnetwork.ui.groups;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.network.model.GroupsDTO;

import static by.grodno.toni7777.socialnetwork.util.Constants.MEDIUM_LIMIT;

import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class GroupsModel implements BaseModel, GroupsMVP.Model {

    private ModelListener<List<GroupDTO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public GroupsModel(ModelListener<List<GroupDTO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, DatabaseDAOImp databaseDAO) {
        mListener = listener;
        mPreferences = preferences;
        mNetworkAPI = networkAPI;
        mDatabaseDAO = databaseDAO;
    }

    @Override
    public void loadGroups(int offset) {
        Observable<GroupsDTO> postsObservable = mNetworkAPI.getGroups(mPreferences.getUserId(), offset, MEDIUM_LIMIT, mPreferences.getAccessToken());
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
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
