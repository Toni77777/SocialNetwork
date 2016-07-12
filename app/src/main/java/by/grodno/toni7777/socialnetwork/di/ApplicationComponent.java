package by.grodno.toni7777.socialnetwork.di;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.login.LoginFragment;
import by.grodno.toni7777.socialnetwork.login.LoginPresenterImp;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(LoginFragment loginFragment);

    void inject(SocialNetworkApp application);
}
