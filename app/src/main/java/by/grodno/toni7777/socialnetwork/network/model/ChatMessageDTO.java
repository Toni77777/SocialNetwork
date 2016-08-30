package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class ChatMessageDTO {

    @SerializedName("senderId")
    private int mSenderId;

    @SerializedName("message")
    private String mMessage;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(int senderId, String message) {
        mSenderId = senderId;
        mMessage = message;
    }

    public int getSenderId() {
        return mSenderId;
    }

    public void setSenderId(int senderId) {
        mSenderId = senderId;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessageDTO that = (ChatMessageDTO) o;

        if (mSenderId != that.mSenderId) return false;
        return mMessage != null ? mMessage.equals(that.mMessage) : that.mMessage == null;

    }

    @Override
    public int hashCode() {
        int result = mSenderId;
        result = 31 * result + (mMessage != null ? mMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "mSenderId=" + mSenderId +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}
