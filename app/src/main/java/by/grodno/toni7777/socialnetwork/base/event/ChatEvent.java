package by.grodno.toni7777.socialnetwork.base.event;

public class ChatEvent {

    private final long mChatId;

    public ChatEvent(long chatId) {
        mChatId = chatId;
    }

    public long getChatId() {
        return mChatId;
    }
}
