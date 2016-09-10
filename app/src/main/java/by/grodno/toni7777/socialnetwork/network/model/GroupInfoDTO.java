package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class GroupInfoDTO {

    @SerializedName("id")
    private long mGroupId;

    @SerializedName("name")
    private String mName;

    @SerializedName("folowers")
    private long mMembers;

    @SerializedName("image")
    private String mGroupAvatar;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("owner")
    private boolean isOwner;

    @SerializedName("member")
    private boolean isMember;

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

    public String getGroupAvatar() {
        return mGroupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        mGroupAvatar = groupAvatar;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    @Override
    public String toString() {
        return "GroupInfoDTO{" +
                "mGroupId=" + mGroupId +
                ", mName='" + mName + '\'' +
                ", mMembers=" + mMembers +
                ", mGroupAvatar='" + mGroupAvatar + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", isOwner=" + isOwner +
                ", isMember=" + isMember +
                '}';
    }
}
