package by.grodno.toni7777.socialnetwork.ui.profile.listener;

import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public interface ProfileListener {

    void onLoadCompleted(ProfileDVO profileDVO);

    void loadError(Throwable e);

}
