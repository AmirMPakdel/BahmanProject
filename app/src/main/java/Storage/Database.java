package Storage;

import io.realm.Realm;

public class Database {

    public static Realm getRealm(){

        return Realm.getDefaultInstance();
    }
}
