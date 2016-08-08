package by.grodno.toni7777.socialnetwork.dagger2.module;

import android.app.Application;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import dagger.Module;
import dagger.Provides;

@Module
public class SocialNetworkAppModule {

    final Application mApplication;

    public SocialNetworkAppModule(SocialNetworkApp application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
