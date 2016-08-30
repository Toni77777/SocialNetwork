package by.grodno.toni7777.socialnetwork.base.event;

public class ChatEvent {

    private long mChatId;
    private String mFriendName;
    private String mFriendAvatar;

    public ChatEvent() {
    }

    public ChatEvent(long chatId, String friendName, String friendAvatar) {
        mChatId = chatId;
        mFriendName = friendName;
        mFriendAvatar = friendAvatar;
    }

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }

    public String getFriendName() {
        return mFriendName;
    }

    public void setFriendName(String friendName) {
        mFriendName = friendName;
    }

    public String getFriendAvatar() {
        return mFriendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        mFriendAvatar = friendAvatar;
    }
}
