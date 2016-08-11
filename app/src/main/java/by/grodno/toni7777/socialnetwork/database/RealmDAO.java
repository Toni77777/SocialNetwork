package by.grodno.toni7777.socialnetwork.database;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public interface RealmDAO {

    <E extends RealmModel> E copyToRealmOrUpdate(Realm realm, final E object);

    void clearDatabase(Realm realm);

    <E extends RealmModel> RealmResults<E> readAll(Realm realm, Class clazz);


    <E extends RealmModel> E findFirst(Realm realm, Class clazz);
}
