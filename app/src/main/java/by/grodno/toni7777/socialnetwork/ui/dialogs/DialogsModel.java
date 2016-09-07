package by.grodno.toni7777.socialnetwork.ui.dialogs;

import android.util.Log;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.mvp.ModelListener;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;
import by.grodno.toni7777.socialnetwork.network.model.DialogsDTO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class DialogsModel implements BaseModel, DialogsMVP.Model {

    private ModelListener<List<DialogDTO>> mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public DialogsModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                        ModelListener<List<DialogDTO>> listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
    }

    @Override
    public void loadDialogs(int offset) {
        Observable<DialogsDTO> dialogsObservable = mNetworkAPI.getDialogs(offset, Constants.MEDIUM_LIMIT, mPreferences.getAccessToken());

        unsubscribe();
        mSubscription = dialogsObservable
                .compose(RxUtil.<DialogsDTO>applySchedulers())
                .subscribe(
                        dialogs -> {
                            mListener.loadNext(dialogs.getDialogs());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadError(throwable);
                            Log.e("Dialog", "Dialog error " + throwable);
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

    public long getMyId() {
        return mPreferences.getUserId();
    }

    @Override
    public String getMyAvatar() {
        return mPreferences.getUserAvatar();
    }
}
