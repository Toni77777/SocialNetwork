package by.grodno.toni7777.socialnetwork.ui.chat.listener;

import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.ChatMessageDTO;

public interface ChatListener {

    void onChatLoadCompleted(List<ChatMessageDTO> message);

    void loadChatIdError(Throwable e);
}
