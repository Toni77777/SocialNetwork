package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class GroupDataDTO {

    @SerializedName("entity")
    private GroupInfoDTO mGroupInfo;

    public GroupInfoDTO getGroupInfo() {
        return mGroupInfo;
    }

    public void setGroupInfo(GroupInfoDTO groupInfo) {
        mGroupInfo = groupInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDataDTO that = (GroupDataDTO) o;

        return mGroupInfo != null ? mGroupInfo.equals(that.mGroupInfo) : that.mGroupInfo == null;

    }

    @Override
    public int hashCode() {
        return mGroupInfo != null ? mGroupInfo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupDataDTO{" +
                "mGroupInfo=" + mGroupInfo +
                '}';
    }
}
