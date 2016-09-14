package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class HistoryMessagesDTO {

    @SerializedName("entity")
    private ChatDTO mChatDTO;

    public ChatDTO getChatDTO() {
        return mChatDTO;
    }

    public void setChatDTO(ChatDTO chatDTO) {
        mChatDTO = chatDTO;
    }
}
