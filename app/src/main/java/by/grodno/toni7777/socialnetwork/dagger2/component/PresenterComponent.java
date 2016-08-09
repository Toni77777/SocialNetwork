package by.grodno.toni7777.socialnetwork.dagger2.component;

import by.grodno.toni7777.socialnetwork.dagger2.module.PresenterModule;
import by.grodno.toni7777.socialnetwork.dagger2.scope.PrefensecScope;
import by.grodno.toni7777.socialnetwork.login.LoginFragment;
import by.grodno.toni7777.socialnetwork.wall.WallFragment;
import dagger.Component;

@PrefensecScope
@Component(dependencies = NetworkComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(WallFragment fragment);

    void inject(LoginFragment fragment);
}
