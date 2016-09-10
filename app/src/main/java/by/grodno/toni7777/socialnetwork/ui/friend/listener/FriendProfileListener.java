package by.grodno.toni7777.socialnetwork.ui.friend.listener;

import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public interface FriendProfileListener {

    void onProfileLoadCompleted(ProfileDVO profileDVO);

    void loadProfileError(Throwable e);

}
