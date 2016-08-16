package by.grodno.toni7777.socialnetwork.database;

import android.util.Log;

import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
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

}
