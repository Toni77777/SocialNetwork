package by.grodno.toni7777.socialnetwork.ui.groups;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.GroupDSO;
import by.grodno.toni7777.socialnetwork.database.model.GroupsDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.GroupsDTO;

import static by.grodno.toni7777.socialnetwork.util.Constants.MEDIUM_LIMIT;

import by.grodno.toni7777.socialnetwork.ui.model.GroupDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDSOtoDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscription;

public class GroupsModel implements BaseModel, GroupsMVP.Model {

    private ModelListener<List<GroupDVO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public GroupsModel(ModelListener<List<GroupDVO>> listener, LoginPreferences preferences, SocialNetworkAPI networkAPI, DatabaseDAOImp databaseDAO) {
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
                .map(ConverterDTOtoDSO::toGroupDSO)
                .doOnNext(groupsDSO -> saveInCache(groupsDSO, offset))
                .subscribe(
                        groups -> {
                        },
                        throwable -> {
                            unsubscribe();
                            readGroupsFromDB(offset);
                            mListener.loadError(throwable);
                        },
                        () -> {
                            unsubscribe();
                            readGroupsFromDB(offset);
                        }
                );
    }

    @Override
    public void saveInCache(GroupsDSO responseGroups, int offset) {
        RealmResults<GroupsDSO> groups = mDatabaseDAO.readAll(Realm.getDefaultInstance(), GroupsDSO.class);
        if (groups.size() == 0) { // have not wall object from DB
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseGroups);
        } else if (offset == 0) { // if refresh to new post
            mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), responseGroups);
        } else {
            RealmList<GroupDSO> response = responseGroups.getGroupsDSO();
            mDatabaseDAO.updateGroups(Realm.getDefaultInstance(), response);
        }
    }

    @Override
    public void readGroupsFromDB(int offset) {
        GroupsDSO groupsDSO = mDatabaseDAO.findFirst(Realm.getDefaultInstance(), GroupsDSO.class);
        if (groupsDSO == null) {
            throw new IllegalArgumentException("GroupsDSO null");
        } else {
            RealmList<GroupDSO> groups = groupsDSO.getGroupsDSO();
            if (groups == null) {
                throw new IllegalArgumentException("Groups from database null");
            }
            List<GroupDVO> readData = ConverterDSOtoDVO.toGroupDVO(groups);
            List<GroupDVO> result = new ArrayList<>();
            int size = readData.size();
            if (size >= (offset + MEDIUM_LIMIT - 1)) {
                result = readData.subList(offset, offset + MEDIUM_LIMIT - 1);
            } else {
                result = readData.subList(offset, size);
            }
            mListener.loadNext(result);
            mListener.onLoadCompleted();
        }
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
