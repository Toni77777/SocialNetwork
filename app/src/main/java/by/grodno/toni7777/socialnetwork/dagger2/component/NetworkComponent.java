package by.grodno.toni7777.socialnetwork.dagger2.component;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.dagger2.module.NetworkModule;
import by.grodno.toni7777.socialnetwork.dagger2.module.SocialNetworkAppModule;
import by.grodno.toni7777.socialnetwork.wall.WallFragment;
import dagger.Component;

@Singleton
@Component(modules = {SocialNetworkAppModule.class, NetworkModule.class})
public interface NetworkComponent {

    void inject(WallFragment fragment);
}
