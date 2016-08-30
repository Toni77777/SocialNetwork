package by.grodno.toni7777.socialnetwork.ui.dialogs;

import android.os.Parcel;
import android.os.Parcelable;

public class ShareDate implements Parcelable {

    private long mChatId;
    private long mId;
    private String mFullname;
    private String mAvatar;
    private String mNameFriend;
    private String mFriendAvatar;

    public ShareDate() {
    }

    public ShareDate(long chatId, long id, String fullname, String avatar, String nameFriend, String friendAvatar) {
        mChatId = chatId;
        mId = id;
        mFullname = fullname;
        mAvatar = avatar;
        mNameFriend = nameFriend;
        mFriendAvatar = friendAvatar;
    }

    public long getChatId() {
        return mChatId;
    }

    public void setChatId(long chatId) {
        mChatId = chatId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getNameFriend() {
        return mNameFriend;
    }

    public void setNameFriend(String nameFriend) {
        mNameFriend = nameFriend;
    }

    public String getFriendAvatar() {
        return mFriendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        mFriendAvatar = friendAvatar;
    }

    @Override
    public String toString() {
        return "ShareDate{" +
                "mChatId=" + mChatId +
                ", mId=" + mId +
                ", mFullname='" + mFullname + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mNameFriend='" + mNameFriend + '\'' +
                ", mFriendAvatar='" + mFriendAvatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShareDate shareDate = (ShareDate) o;

        if (mChatId != shareDate.mChatId) return false;
        if (mId != shareDate.mId) return false;
        if (mFullname != null ? !mFullname.equals(shareDate.mFullname) : shareDate.mFullname != null)
            return false;
        if (mAvatar != null ? !mAvatar.equals(shareDate.mAvatar) : shareDate.mAvatar != null)
            return false;
        if (mNameFriend != null ? !mNameFriend.equals(shareDate.mNameFriend) : shareDate.mNameFriend != null)
            return false;
        return mFriendAvatar != null ? mFriendAvatar.equals(shareDate.mFriendAvatar) : shareDate.mFriendAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mChatId ^ (mChatId >>> 32));
        result = 31 * result + (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mFullname != null ? mFullname.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mNameFriend != null ? mNameFriend.hashCode() : 0);
        result = 31 * result + (mFriendAvatar != null ? mFriendAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mChatId);
        parcel.writeLong(mId);
        parcel.writeString(mFullname);
        parcel.writeString(mAvatar);
        parcel.writeString(mNameFriend);
        parcel.writeString(mFriendAvatar);

    }

    protected ShareDate(Parcel in) {
        mChatId = in.readLong();
        mId = in.readLong();
        mFullname = in.readString();
        mAvatar = in.readString();
        mNameFriend = in.readString();
        mFriendAvatar = in.readString();
    }

    public static final Creator<ShareDate> CREATOR = new Creator<ShareDate>() {
        @Override
        public ShareDate createFromParcel(Parcel parcel) {
            return new ShareDate(parcel);
        }

        @Override
        public ShareDate[] newArray(int size) {
            return new ShareDate[size];
        }
    };

}
