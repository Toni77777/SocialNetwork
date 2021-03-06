package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.SocialNetworkAppEventBusIndex;
import by.grodno.toni7777.socialnetwork.dagger2.component.DaggerNetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.DaggerPresenterComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.NetworkComponent;
import by.grodno.toni7777.socialnetwork.dagger2.component.PresenterComponent;
import by.grodno.toni7777.socialnetwork.dagger2.module.NetworkModule;
import by.grodno.toni7777.socialnetwork.dagger2.module.PresenterModule;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SocialNetworkApp extends Application {

    private NetworkComponent mNetworkComponent;
    private PresenterComponent mPresenterComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

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

