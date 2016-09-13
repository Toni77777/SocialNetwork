package by.grodno.toni7777.socialnetwork.ui.restore;


import android.util.Log;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.ui.restore.listener.RestoreListener;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class RestoreModel implements BaseModel, RestoreMVP.Model {
    private SocialNetworkAPI mNetworkAPI;
    private Subscription mSubscription;
    private RestoreListener mListener;

    public RestoreModel(SocialNetworkAPI socialNetworkAPI, RestoreListener restoreListener) {
        mNetworkAPI = socialNetworkAPI;
        mListener = restoreListener;
    }

    @Override
    public void restorePassword(String email) {
        Observable<ResponseDTO> restoreObservable = mNetworkAPI.restorePassword(email);
        mSubscription = restoreObservable
                .compose(RxUtil.<ResponseDTO>applySchedulers())
                .subscribe(
                        response -> {
                            if (response.isSuccess()) {
                                mListener.onRestoreSuccess();
                            }
                        },
                        throwable -> {
                            mListener.restoreError(throwable);
                            unsubscribe();
                        },
                        () -> {
                            unsubscribe();
                        });
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
