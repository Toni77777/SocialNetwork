package by.grodno.toni7777.socialnetwork.database;

import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class DatabaseDAOImp implements DatabaseDAO {

    @Override
    public <E extends RealmModel> E copyToDatabaseOrUpdate(Realm realm, final E object) {
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(object));
        return object;
    }

    @Override
    public void clearDatabase(Realm realm) {
        realm.executeTransactionAsync(realm1 -> realm1.deleteAll());
    }

    @Override
    public <E extends RealmModel> RealmResults<E> readAll(Realm realm, Class clazz) {
        RealmResults<E> result = realm.where(clazz)
                .findAll();
        return (RealmResults<E>) result;
    }

    @Override
    public <E extends RealmModel> E findFirst(Realm realm, Class clazz) {
        RealmResults<E> result = readAll(realm, clazz);
        return result.first();
    }

    @Override
    public <E extends RealmModel> E findLast(Realm realm, Class clazz) {
        RealmResults<E> result = readAll(realm, clazz);
        return result.last();
    }

    @Override
    public void updateWall(Realm realm, RealmList<PostDSO> newPosts) {
        realm.executeTransaction(realm1 -> {
            List<PostDSO> posts = realm1.copyToRealm(newPosts);
            WallDSO wall = realm1.where(WallDSO.class).equalTo("key", 2).findFirst();
            wall.getPostDSO().addAll(posts);
        });
    }
}
