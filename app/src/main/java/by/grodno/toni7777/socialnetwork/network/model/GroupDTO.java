package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class GroupDTO {

    @SerializedName("id")
    private long mGroupId;

    @SerializedName("name")
    private String mName;

    @SerializedName("folowers")
    private long mMembers;

    @SerializedName("image")
    private String mImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("member")
    private boolean mMember;

    @SerializedName("owner")
    private boolean mOwner;

    public long getGroupId() {
        return mGroupId;
    }

    public void setGroupId(long groupId) {
        mGroupId = groupId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getMembers() {
        return mMembers;
    }

    public void setMembers(long members) {
        mMembers = members;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isMember() {
        return mMember;
    }

    public void setMember(boolean member) {
        mMember = member;
    }

    public boolean isOwner() {
        return mOwner;
    }

    public void setOwner(boolean owner) {
        mOwner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDTO groupDTO = (GroupDTO) o;

        if (mGroupId != groupDTO.mGroupId) return false;
        if (mMembers != groupDTO.mMembers) return false;
        if (mMember != groupDTO.mMember) return false;
        if (mOwner != groupDTO.mOwner) return false;
        if (mName != null ? !mName.equals(groupDTO.mName) : groupDTO.mName != null) return false;
        if (mImage != null ? !mImage.equals(groupDTO.mImage) : groupDTO.mImage != null)
            return false;
        return mDescription != null ? mDescription.equals(groupDTO.mDescription) : groupDTO.mDescription == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mGroupId ^ (mGroupId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (int) (mMembers ^ (mMembers >>> 32));
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + (mMember ? 1 : 0);
        result = 31 * result + (mOwner ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "mGroupId=" + mGroupId +
                ", mName='" + mName + '\'' +
                ", mMembers=" + mMembers +
                ", mImage='" + mImage + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mMember=" + mMember +
                ", mOwner=" + mOwner +
                '}';
    }
}
