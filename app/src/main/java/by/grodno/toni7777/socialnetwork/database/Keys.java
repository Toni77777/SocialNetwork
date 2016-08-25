package by.grodno.toni7777.socialnetwork.database;

/**
 * Need save keys from this class,
 * because if you make FINAL Key from instenseof RealmObject,
 * Project not compile, key not make final
 * Have final key, because SoN have singleton objects from Database
 */
public final class Keys {

    public static final int PROFILE_KEY = 1;
    public static final int WALL_KEY = 2;
    public static final int FRIENDS_KEY = 3;


    /**
     * All singleton on Database DSO object must have field key with name "key"
     */
    public static final String FIELD_KEY = "key";

    private Keys() {
    }
}
