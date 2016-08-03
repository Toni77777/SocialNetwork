package by.grodno.toni7777.socialnetwork.dagger2.module;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import dagger.Module;
import dagger.Provides;

@Module
public class SocialNetworkAppModule {

    final SocialNetworkApp mApplication;

    public SocialNetworkAppModule(SocialNetworkApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    SocialNetworkApp provideApplication() {
        return mApplication;
    }
}
