package by.grodno.toni7777.socialnetwork.dagger2.module;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.dagger2.scope.PrefensecScope;
import by.grodno.toni7777.socialnetwork.login.LoginPresenter;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.wall.WallPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @PrefensecScope
    @Provides
    public WallPresenter provideWallPresenter(SocialNetworkAPI socialNetworkAPI) {
        return new WallPresenter(socialNetworkAPI);
    }

    @PrefensecScope
    @Provides
    public LoginPresenter provideLoginPresenter(SocialNetworkAPI socialNetworkAPI) {
        return new LoginPresenter(socialNetworkAPI);
    }
}
