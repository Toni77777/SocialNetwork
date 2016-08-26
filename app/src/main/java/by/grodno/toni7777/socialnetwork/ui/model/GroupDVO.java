package by.grodno.toni7777.socialnetwork.ui.model;

public class GroupDVO {

    private long mGroupId;
    private String mName;
    private long mMembers;
    private String mImage;
    private String mDescription;
    private boolean mMember;
    private boolean mOwner;

    public GroupDVO() {
    }

    public GroupDVO(long groupId, String name, long members, String image, String description, boolean member, boolean owner) {
        mGroupId = groupId;
        mName = name;
        mMembers = members;
        mImage = image;
        mDescription = description;
        mMember = member;
        mOwner = owner;
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

        GroupDVO groupDVO = (GroupDVO) o;

        if (mGroupId != groupDVO.mGroupId) return false;
        if (mMembers != groupDVO.mMembers) return false;
        if (mMember != groupDVO.mMember) return false;
        if (mOwner != groupDVO.mOwner) return false;
        if (mName != null ? !mName.equals(groupDVO.mName) : groupDVO.mName != null) return false;
        if (mImage != null ? !mImage.equals(groupDVO.mImage) : groupDVO.mImage != null)
            return false;
        return mDescription != null ? mDescription.equals(groupDVO.mDescription) : groupDVO.mDescription == null;

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
        return "GroupDVO{" +
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
