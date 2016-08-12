package by.grodno.toni7777.socialnetwork.ui.profile;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.database.model.ContactProfileDSO;
import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.mvp.BaseListener;
import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import by.grodno.toni7777.socialnetwork.ui.profile.listener.ProfileListener;
import by.grodno.toni7777.socialnetwork.util.ConverterDSOtoDVO;
import by.grodno.toni7777.socialnetwork.util.ConverterDTOtoDSO;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import io.realm.Realm;
import rx.Observable;
import rx.Subscription;

public class ProfileModel implements BaseModel, ProfileMVP.ProfileModel {

    private ProfileListener mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;
    private DatabaseDAOImp mDatabaseDAO;

    public ProfileModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO, ProfileListener listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mDatabaseDAO = databaseDAO;
        this.mListener = listener;
    }

    @Override
    public void loadProfileInfo() {
        Observable<ProfileDTO> profileObservable = mNetworkAPI.getProfileInfo(mPreferences.getAccessToken());

        mSubscription = profileObservable
                .compose(RxUtil.<ProfileDTO>applySchedulers())
                .map(ConverterDTOtoDSO::converteDTOtoDSO)
                .doOnNext(profileDSO -> mDatabaseDAO.copyToDatabaseOrUpdate(Realm.getDefaultInstance(), profileDSO))
                .subscribe(
                        profile -> {
                            readProfileFromDB();
                        },
                        throwable -> {
                            unsubscribe();
                            readProfileFromDB();
                        },
                        () -> {
                            unsubscribe();
                        });
    }

    public void readProfileFromDB() {
        ProfileDSO profileDSO = mDatabaseDAO.findFirst(Realm.getDefaultInstance(), ProfileDSO.class);
        ProfileDVO profileView = ConverterDSOtoDVO.converteDTOtoDSO(profileDSO);
        mListener.onLoadCompleted(profileView);
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
