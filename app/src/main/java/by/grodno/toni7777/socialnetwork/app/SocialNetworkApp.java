package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import by.grodno.toni7777.socialnetwork.di.ApplicationComponent;
import by.grodno.toni7777.socialnetwork.di.ApplicationModule;
import by.grodno.toni7777.socialnetwork.di.DaggerApplicationComponent;
import by.grodno.toni7777.socialnetwork.network.NetworkService;

public class SocialNetworkApp extends Application {

    private ApplicationComponent mComponent;
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public static SocialNetworkApp from(@NonNull Context context) {
        return (SocialNetworkApp) context.getApplicationContext();
    }
}
