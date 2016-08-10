package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

public class PostRemoveDTO {

    @SerializedName("entity")
    private boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Override
    public String toString() {
        return "PostRemoveDTO{" +
                "remove=" + remove +
                '}';
    }
}
