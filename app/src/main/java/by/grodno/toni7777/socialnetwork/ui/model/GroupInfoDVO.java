package by.grodno.toni7777.socialnetwork.ui.model;

public class GroupInfoDVO {

    private long mGroupId;
    private String mName;
    private long mMembers;
    private String mGroupAvatar;
    private String mDescription;
    private boolean isOwner;
    private boolean isMember;

    public GroupInfoDVO() {
    }

    public GroupInfoDVO(long groupId, String name, long members, String groupAvatar, String description, boolean isOwner, boolean isMember) {
        mGroupId = groupId;
        mName = name;
        mMembers = members;
        mGroupAvatar = groupAvatar;
        mDescription = description;
        this.isOwner = isOwner;
        this.isMember = isMember;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupInfoDVO that = (GroupInfoDVO) o;

        if (mGroupId != that.mGroupId) return false;
        if (mMembers != that.mMembers) return false;
        if (isOwner != that.isOwner) return false;
        if (isMember != that.isMember) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mGroupAvatar != null ? !mGroupAvatar.equals(that.mGroupAvatar) : that.mGroupAvatar != null)
            return false;
        return mDescription != null ? mDescription.equals(that.mDescription) : that.mDescription == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mGroupId ^ (mGroupId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (int) (mMembers ^ (mMembers >>> 32));
        result = 31 * result + (mGroupAvatar != null ? mGroupAvatar.hashCode() : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + (isOwner ? 1 : 0);
        result = 31 * result + (isMember ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupInfoDVO{" +
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
