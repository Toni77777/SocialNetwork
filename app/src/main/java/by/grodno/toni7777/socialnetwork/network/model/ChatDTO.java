package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatDTO {

    @SerializedName("messages")
    private List<ChatMessageDTO> mChatMessages;

    public List<ChatMessageDTO> getChatMessages() {
        return mChatMessages;
    }

    public void setChatMessages(List<ChatMessageDTO> chatMessages) {
        mChatMessages = chatMessages;
    }
}
