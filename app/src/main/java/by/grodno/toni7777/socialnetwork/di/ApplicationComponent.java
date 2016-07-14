package by.grodno.toni7777.socialnetwork.di;

import by.grodno.toni7777.socialnetwork.login.LoginPresenterImp;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(LoginPresenterImp login);//remove

}
