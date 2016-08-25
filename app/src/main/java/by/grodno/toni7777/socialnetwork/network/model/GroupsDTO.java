package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupsDTO {

    @SerializedName("entity")
    private List<GroupDTO> mGroups;

    public List<GroupDTO> getGroups() {
        return mGroups;
    }

    public void setGroups(List<GroupDTO> groups) {
        mGroups = groups;
    }
}
