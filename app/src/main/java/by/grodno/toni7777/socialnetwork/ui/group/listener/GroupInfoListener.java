package by.grodno.toni7777.socialnetwork.ui.group.listener;

import by.grodno.toni7777.socialnetwork.ui.model.GroupInfoDVO;

public interface GroupInfoListener {

    void onInfoLoadCompleted(GroupInfoDVO groupInfo);

    void loadInfoError(Throwable e);
}
