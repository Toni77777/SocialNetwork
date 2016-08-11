package by.grodno.toni7777.socialnetwork.dagger2.component;

import by.grodno.toni7777.socialnetwork.dagger2.module.PresenterModule;
import by.grodno.toni7777.socialnetwork.dagger2.scope.PresenterScope;
import by.grodno.toni7777.socialnetwork.ui.login.LoginFragment;
import by.grodno.toni7777.socialnetwork.ui.wall.WallFragment;
import dagger.Component;

@PresenterScope
@Component(dependencies = NetworkComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(WallFragment fragment);

    void inject(LoginFragment fragment);
}
