package by.grodno.toni7777.socialnetwork.dagger2.component;

import by.grodno.toni7777.socialnetwork.dagger2.module.PresenterModule;
import by.grodno.toni7777.socialnetwork.dagger2.scope.PresenterScope;
import by.grodno.toni7777.socialnetwork.ui.dialogs.DialogsFragment;
import by.grodno.toni7777.socialnetwork.ui.friend.FriendFragment;
import by.grodno.toni7777.socialnetwork.ui.group.GroupFragment;
import by.grodno.toni7777.socialnetwork.ui.groups.GroupsFragment;
import by.grodno.toni7777.socialnetwork.ui.login.LoginFragment;
import by.grodno.toni7777.socialnetwork.ui.newpost.NewPostFragment;
import by.grodno.toni7777.socialnetwork.ui.friends.FriendsFragment;
import by.grodno.toni7777.socialnetwork.ui.profile.ProfileFragment;
import by.grodno.toni7777.socialnetwork.ui.registration.fragment.ContactFragment;
import by.grodno.toni7777.socialnetwork.ui.restore.RestoreFragment;
import by.grodno.toni7777.socialnetwork.ui.search.groups.SearchGroupsFragment;
import by.grodno.toni7777.socialnetwork.ui.search.persons.PersonsFragment;
import by.grodno.toni7777.socialnetwork.ui.wall.WallFragment;
import dagger.Component;

@PresenterScope
@Component(dependencies = NetworkComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(WallFragment fragment);

    void inject(LoginFragment fragment);

    void inject(NewPostFragment fragment);

    void inject(ProfileFragment fragment);

    void inject(ContactFragment fragment);

    void inject(FriendsFragment fragment);

    void inject(PersonsFragment fragment);

    void inject(GroupsFragment fragment);

    void inject(SearchGroupsFragment fragment);

    void inject(GroupFragment fragment);

    void inject(FriendFragment fragment);

    void inject(DialogsFragment fragment);

    void inject(RestoreFragment fragment);

}
