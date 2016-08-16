package by.grodno.toni7777.socialnetwork.database;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public interface DatabaseDAO {

    <E extends RealmModel> E copyToDatabaseOrUpdate(Realm realm, final E object);

    void clearDatabase(Realm realm);

    <E extends RealmModel> RealmResults<E> readAll(Realm realm, Class clazz);

    <E extends RealmModel> E findFirst(Realm realm, Class clazz);

    <E extends RealmModel> E findLast(Realm realm, Class clazz);
}
