package by.grodno.toni7777.socialnetwork.dagger2.module;


import by.grodno.toni7777.socialnetwork.dagger2.scope.PresenterScope;
import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.ui.chat.ChatPresenter;
import by.grodno.toni7777.socialnetwork.ui.dialogs.DialogsPresenter;
import by.grodno.toni7777.socialnetwork.ui.friend.FriendPresenter;
import by.grodno.toni7777.socialnetwork.ui.group.GroupPresenter;
import by.grodno.toni7777.socialnetwork.ui.groups.GroupsPresenter;
import by.grodno.toni7777.socialnetwork.ui.login.LoginPresenter;
import by.grodno.toni7777.socialnetwork.ui.friends.FriendsPresenter;
import by.grodno.toni7777.socialnetwork.ui.profile.ProfilePresenter;
import by.grodno.toni7777.socialnetwork.ui.registration.fragment.ContactPresenter;
import by.grodno.toni7777.socialnetwork.ui.restore.RestorePresenter;
import by.grodno.toni7777.socialnetwork.ui.search.groups.SearchGroupsPresenter;
import by.grodno.toni7777.socialnetwork.ui.search.persons.PersonsPresenter;
import by.grodno.toni7777.socialnetwork.ui.wall.WallPresenter;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @PresenterScope
    @Provides
    public WallPresenter provideWallPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        return new WallPresenter(socialNetworkAPI, loginPreferences, databaseDAO);
    }

    @PresenterScope
    @Provides
    public LoginPresenter provideLoginPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        return new LoginPresenter(socialNetworkAPI, loginPreferences, databaseDAO);
    }

    @PresenterScope
    @Provides
    public ProfilePresenter provideProfilePresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        return new ProfilePresenter(socialNetworkAPI, loginPreferences, databaseDAO);
    }

    @PresenterScope
    @Provides
    public ContactPresenter provideContactPresenter(SocialNetworkAPI socialNetworkAPI) {
        return new ContactPresenter(socialNetworkAPI);
    }

    @PresenterScope
    @Provides
    public FriendsPresenter provideFriendsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        return new FriendsPresenter(socialNetworkAPI, loginPreferences, databaseDAO);
    }

    @PresenterScope
    @Provides
    public PersonsPresenter providePersonsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new PersonsPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public GroupsPresenter provideGroupsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences, DatabaseDAOImp databaseDAO) {
        return new GroupsPresenter(socialNetworkAPI, loginPreferences, databaseDAO);
    }

    @PresenterScope
    @Provides
    public SearchGroupsPresenter provideSearchGroupsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new SearchGroupsPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public GroupPresenter provideGroupPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new GroupPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public FriendPresenter provideFriendPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new FriendPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public DialogsPresenter provideDialogsPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new DialogsPresenter(socialNetworkAPI, loginPreferences);
    }

    @PresenterScope
    @Provides
    public RestorePresenter provideRestorePresenter(SocialNetworkAPI socialNetworkAPI) {
        return new RestorePresenter(socialNetworkAPI);
    }

    @PresenterScope
    @Provides
    public ChatPresenter provideChatPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        return new ChatPresenter(socialNetworkAPI, loginPreferences);
    }

}
