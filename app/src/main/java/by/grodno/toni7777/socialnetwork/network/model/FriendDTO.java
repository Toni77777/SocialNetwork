package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class FriendDTO extends PersonDTO {

    @SerializedName("online")
    private boolean mOnline;

    public FriendDTO() {
    }

    public FriendDTO(String name, String surname, long id, String avatar, boolean online) {
        super(name, surname, id, avatar);
        mOnline = online;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public void setOnline(boolean online) {
        mOnline = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FriendDTO friendDTO = (FriendDTO) o;

        return mOnline == friendDTO.mOnline;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mOnline ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendDTO{" +
                "mOnline=" + mOnline +
                '}';
    }
}
