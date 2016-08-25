package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsDTO {

    @SerializedName("entity")
    private List<FriendDTO> mFriends;

    @SerializedName("metadata")
    private MetadataDTO mMetadata;

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    public void setFriends(List<FriendDTO> friends) {
        mFriends = friends;
    }

    public MetadataDTO getMetadata() {
        return mMetadata;
    }

    public void setMetadata(MetadataDTO metadata) {
        mMetadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsDTO that = (FriendsDTO) o;

        if (mFriends != null ? !mFriends.equals(that.mFriends) : that.mFriends != null)
            return false;
        return mMetadata != null ? mMetadata.equals(that.mMetadata) : that.mMetadata == null;

    }

    @Override
    public int hashCode() {
        int result = mFriends != null ? mFriends.hashCode() : 0;
        result = 31 * result + (mMetadata != null ? mMetadata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendsDTO{" +
                "mFriends=" + mFriends +
                ", mMetadata=" + mMetadata +
                '}';
    }
}
