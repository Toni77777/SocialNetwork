package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;

import by.grodno.toni7777.socialnetwork.di.ApplicationComponent;
import by.grodno.toni7777.socialnetwork.di.ApplicationModule;
import by.grodno.toni7777.socialnetwork.di.DaggerApplicationComponent;

public class SocialNetworkApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mApplicationComponent = null;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
