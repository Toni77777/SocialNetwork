package by.grodno.toni7777.socialnetwork.base.event;

public class ChatIdEvent {

    private long mChatId;

    public ChatIdEvent(long chatId) {
        mChatId = chatId;
    }

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }
}
