package by.grodno.toni7777.socialnetwork.model;

import java.util.List;

public class Group {

    private long groupID;
    private String name;
    private String description;
    private Wall wall;
    private List<Long> members;
    private long adminID;

    public Group() {
    }

    public Group(long groupID, String name, String description, Wall wall, List<Long> membors, long adminID) {
        this.groupID = groupID;
        this.name = name;
        this.description = description;
        this.wall = wall;
        this.members = membors;
        this.adminID = adminID;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public List<Long> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }

    public long getAdminID() {
        return adminID;
    }

    public void setAdminID(long adminID) {
        this.adminID = adminID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupID != group.groupID) return false;
        if (adminID != group.adminID) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (description != null ? !description.equals(group.description) : group.description != null)
            return false;
        if (wall != null ? !wall.equals(group.wall) : group.wall != null) return false;
        return members != null ? members.equals(group.members) : group.members == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (groupID ^ (groupID >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (wall != null ? wall.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (int) (adminID ^ (adminID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", wall=" + wall +
                ", members=" + members +
                ", adminID=" + adminID +
                '}';
    }
}
