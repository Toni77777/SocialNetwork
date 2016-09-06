package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DialogDTO {

    @SerializedName("id")
    private long mChatId;

    @SerializedName("lastMessage")
    private ChatMessageDTO mChatMessage;

    @SerializedName("reciver")
    private List<FriendDTO> mFriends;

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }

    public ChatMessageDTO getChatMessage() {
        return mChatMessage;
    }

    public void setChatMessage(ChatMessageDTO chatMessage) {
        mChatMessage = chatMessage;
    }

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    public void setFriends(List<FriendDTO> friends) {
        mFriends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialogDTO dialogDTO = (DialogDTO) o;

        if (mChatId != dialogDTO.mChatId) return false;
        if (mChatMessage != null ? !mChatMessage.equals(dialogDTO.mChatMessage) : dialogDTO.mChatMessage != null)
            return false;
        return mFriends != null ? mFriends.equals(dialogDTO.mFriends) : dialogDTO.mFriends == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mChatId ^ (mChatId >>> 32));
        result = 31 * result + (mChatMessage != null ? mChatMessage.hashCode() : 0);
        result = 31 * result + (mFriends != null ? mFriends.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DialogDTO{" +
                "mChatId=" + mChatId +
                ", mChatMessage=" + mChatMessage +
                ", mFriends=" + mFriends +
                '}';
    }
}
