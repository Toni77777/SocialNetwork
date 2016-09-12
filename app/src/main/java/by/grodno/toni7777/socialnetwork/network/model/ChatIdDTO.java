package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ChatIdDTO {

    @SerializedName("entity")
    private long mChatId;

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }
}
