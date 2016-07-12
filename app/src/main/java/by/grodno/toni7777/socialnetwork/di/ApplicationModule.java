package by.grodno.toni7777.socialnetwork.di;


import android.content.Context;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.network.NetworkService;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final SocialNetworkApp mApplication;
    private NetworkService mNetworkService;

    public ApplicationModule(SocialNetworkApp application) {
        mApplication = application;
        mNetworkService = new NetworkService();
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService() {
        return mNetworkService;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }
}