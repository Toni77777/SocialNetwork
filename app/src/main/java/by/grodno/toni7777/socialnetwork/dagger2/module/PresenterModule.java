package by.grodno.toni7777.socialnetwork.dagger2.module;


import by.grodno.toni7777.socialnetwork.dagger2.scope.PresenterScope;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.login.LoginPresenter;
import by.grodno.toni7777.socialnetwork.ui.wall.WallPresenter;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
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
