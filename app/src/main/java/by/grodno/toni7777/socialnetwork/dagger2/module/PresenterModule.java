package by.grodno.toni7777.socialnetwork.dagger2.module;


import by.grodno.toni7777.socialnetwork.dagger2.scope.PresenterScope;
import by.grodno.toni7777.socialnetwork.login.LoginPresenter;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.wall.WallPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @PresenterScope
    @Provides
    public WallPresenter provideWallPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new WallPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public LoginPresenter provideLoginPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new LoginPresenter(socialNetworkAPI, loginPreferences);
    }
}
