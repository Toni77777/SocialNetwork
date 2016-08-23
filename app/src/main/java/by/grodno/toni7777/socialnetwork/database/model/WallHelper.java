package by.grodno.toni7777.socialnetwork.database.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WallHelper extends RealmObject {

    @PrimaryKey
    private String key;
    private RealmList<PostDSO> posts;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public RealmList<PostDSO> getPosts() {
        return posts;
    }

    public void setPosts(RealmList<PostDSO> posts) {
        this.posts = posts;
    }
}

