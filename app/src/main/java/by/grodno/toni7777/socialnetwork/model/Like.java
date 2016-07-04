package by.grodno.toni7777.socialnetwork.model;

import java.util.List;

public class Like {

    private List<Long> usersId;

    public Like() {
    }

    public Like(List<Long> usersId) {
        this.usersId = usersId;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Long> usersId) {
        this.usersId = usersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Like like = (Like) o;

        return usersId != null ? usersId.equals(like.usersId) : like.usersId == null;

    }

    @Override
    public int hashCode() {
        return usersId != null ? usersId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Like{" +
                "usersId=" + usersId +
                '}';
    }
}
