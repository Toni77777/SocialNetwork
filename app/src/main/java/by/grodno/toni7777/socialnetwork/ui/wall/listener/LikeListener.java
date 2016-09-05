package by.grodno.toni7777.socialnetwork.ui.wall.listener;

import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public interface LikeListener {

    void sendLikeCompleted(PostDVO post);

    void sendLikeError(Throwable e);
}
