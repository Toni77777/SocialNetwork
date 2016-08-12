package by.grodno.toni7777.socialnetwork.dagger2.component;


import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.dagger2.module.NetworkModule;
import by.grodno.toni7777.socialnetwork.dagger2.module.SocialNetworkAppModule;
import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import dagger.Component;

@Singleton
@Component(modules = {SocialNetworkAppModule.class, NetworkModule.class})
public interface NetworkComponent {

    SocialNetworkAPI api();

    LoginPreferences loginPreferences();

    DatabaseDAOImp databaseDAO();
}
