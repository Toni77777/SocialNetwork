package by.grodno.toni7777.socialnetwork.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GroupDSO extends RealmObject {

    @PrimaryKey
    private String mKey;
    private long mGroupId;
    private String mName;
    private long mMembers;
    private String mImage;
    private String mDescription;
    private boolean mMember;
    private boolean mOwner;

    public GroupDSO() {
    }

    public GroupDSO(String key, long groupId, String name, long members, String image, String description, boolean member, boolean owner) {
        mKey = key;
        mGroupId = groupId;
        mName = name;
        mMembers = members;
        mImage = image;
        mDescription = description;
        mMember = member;
        mOwner = owner;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
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

        GroupDSO groupDSO = (GroupDSO) o;

        if (mGroupId != groupDSO.mGroupId) return false;
        if (mMembers != groupDSO.mMembers) return false;
        if (mMember != groupDSO.mMember) return false;
        if (mOwner != groupDSO.mOwner) return false;
        if (mKey != null ? !mKey.equals(groupDSO.mKey) : groupDSO.mKey != null) return false;
        if (mName != null ? !mName.equals(groupDSO.mName) : groupDSO.mName != null) return false;
        if (mImage != null ? !mImage.equals(groupDSO.mImage) : groupDSO.mImage != null)
            return false;
        return mDescription != null ? mDescription.equals(groupDSO.mDescription) : groupDSO.mDescription == null;

    }

    @Override
    public int hashCode() {
        int result = mKey != null ? mKey.hashCode() : 0;
        result = 31 * result + (int) (mGroupId ^ (mGroupId >>> 32));
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
        return "GroupDSO{" +
                "mKey='" + mKey + '\'' +
                ", mGroupId=" + mGroupId +
                ", mName='" + mName + '\'' +
                ", mMembers=" + mMembers +
                ", mImage='" + mImage + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mMember=" + mMember +
                ", mOwner=" + mOwner +
                '}';
    }
}
