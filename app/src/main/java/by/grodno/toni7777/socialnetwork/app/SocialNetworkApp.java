package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.SocialNetworkAppEventBusIndex;
import by.grodno.toni7777.socialnetwork.dagger2.component.DaggerNetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.DaggerPresenterComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.NetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.PresenterComponent;
import by.grodno.toni7777.socialnetwork.dagger2.module.NetworkModule;
import by.grodno.toni7777.socialnetwork.dagger2.module.PresenterModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SocialNetworkApp extends Application {

    private NetworkComponent mNetworkComponent;
    private PresenterComponent mPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        EventBus.builder()
                .addIndex(new SocialNetworkAppEventBusIndex())
                .installDefaultEventBus();

        mNetworkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_URL, this))
                .build();

        mPresenterComponent = DaggerPresenterComponent.builder()
                .networkComponent(mNetworkComponent)
                .presenterModule(new PresenterModule())
                .build();

    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }

    public PresenterComponent getPresenterComponent() {
        return mPresenterComponent;
    }
}

