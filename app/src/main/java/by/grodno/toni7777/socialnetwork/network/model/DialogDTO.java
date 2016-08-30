package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DialogDTO {

    @SerializedName("id")
    private long mChatId;

    @SerializedName("lastMessage")
    private String mLastMessage;

    @SerializedName("reciver")
    private List<FriendDTO> mFriends;

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public void setLastMessage(String lastMessage) {
        mLastMessage = lastMessage;
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
        if (mLastMessage != null ? !mLastMessage.equals(dialogDTO.mLastMessage) : dialogDTO.mLastMessage != null)
            return false;
        return mFriends != null ? mFriends.equals(dialogDTO.mFriends) : dialogDTO.mFriends == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mChatId ^ (mChatId >>> 32));
        result = 31 * result + (mLastMessage != null ? mLastMessage.hashCode() : 0);
        result = 31 * result + (mFriends != null ? mFriends.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DialogDTO{" +
                "mChatId=" + mChatId +
                ", mLastMessage='" + mLastMessage + '\'' +
                ", mFriends=" + mFriends +
                '}';
    }
}
