package by.grodno.toni7777.socialnetwork.ui.friend.listener;

import by.grodno.toni7777.socialnetwork.ui.model.ChatIdDVO;

public interface ChatListener {

    void onChatLoadCompleted(ChatIdDVO chatId);

    void loadChatIdError(Throwable e);
}
