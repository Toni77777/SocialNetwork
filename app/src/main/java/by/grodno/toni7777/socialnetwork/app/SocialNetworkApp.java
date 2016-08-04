package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.dagger2.component.DaggerNetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.NetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.module.NetworkModule;

public class SocialNetworkApp extends Application {

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetworkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}

