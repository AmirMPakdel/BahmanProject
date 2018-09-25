package Storage;


import RealmObjects.SharedPreferences;
import Utils.Consts;
import Utils.log;
import io.realm.Realm;


public class StorageBox {

    private static StorageBox storageBox;

    private Realm realm;

    public static SharedPreferences sharedPreferences;

    private StorageBox(){

        realm = Database.getRealm();

        sharedPreferences = realm.where(SharedPreferences.class).findFirst();

        if(sharedPreferences == null){

            SharedPreferences sp = new SharedPreferences(true);

            Consts.AppFirstTimeRun = true;

            realm.beginTransaction();
            realm.copyToRealm(sp);
            realm.commitTransaction();

            sharedPreferences = sp;
        }

        log.print("App First Time Run : "+Consts.AppFirstTimeRun);
    }

    public static synchronized StorageBox init(){

        if(storageBox == null){

            storageBox = new StorageBox();
        }

        return storageBox;
    }

    public static synchronized StorageBox getInstance(){

        if(storageBox == null){

            throw new NullPointerException("StorageBox Must be initialized first => (call StorageBox.init(); )");
        }

        return storageBox;
    }

    public SharedPreferences getSharedPreferences(){

        return realm.where(SharedPreferences.class).findFirst();
    }

    public void updateSharedPreferences(SharedPreferences sp, boolean fistTimeRun, boolean isGuest){

        SharedPreferences dbSp = realm.where(SharedPreferences.class).findFirst();

        realm.beginTransaction();

        dbSp.setFirstTimeRun(fistTimeRun);
        dbSp.setGuest(isGuest);

        if(sp.getId() != null){dbSp.setId(sp.getId());}
        if(sp.getUsername() != null){dbSp.setUsername(sp.getUsername());}
        if(sp.getField() != null){dbSp.setField(sp.getField());}
        if(sp.getCity() != null){dbSp.setCity(sp.getCity());}
        if(sp.getEmail() != null){dbSp.setEmail(sp.getEmail());}
        if(sp.getGrade() != null){dbSp.setGrade(sp.getGrade());}
        if(sp.getPassword() != null){dbSp.setPassword(sp.getPassword());}
        if(sp.getSchoolName() != null){dbSp.setSchoolName(sp.getSchoolName());}
        if(sp.getToken() != null){dbSp.setToken(sp.getToken());}
        if(sp.getScore() == 0.0){dbSp.setScore(sp.getScore());}
        if(sp.getPhoneNumber() == 0.0){dbSp.setPhoneNumber(sp.getPhoneNumber());}
        if(sp.getKEY_RSA_PUBLIC_KEY() != null){dbSp.setKEY_RSA_PUBLIC_KEY(sp.getKEY_RSA_PUBLIC_KEY());}

        realm.commitTransaction();

        sharedPreferences = dbSp;
    }


    public void setIsFirstTime(boolean isFirstTime){

        realm.beginTransaction();

        try {

            sharedPreferences.setFirstTimeRun(isFirstTime);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setIsGuest(boolean isGuest){

        realm.beginTransaction();

        try {

            sharedPreferences.setGuest(isGuest);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setId(String id){

        realm.beginTransaction();

        try {

            sharedPreferences.setId(id);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setGrade(String grade){

        realm.beginTransaction();

        try {

            sharedPreferences.setGrade(grade);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setField(String field){

        realm.beginTransaction();

        try {

            sharedPreferences.setField(field);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setToken(String token){

        realm.beginTransaction();

        try {

            sharedPreferences.setToken(token);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setUsername(String username) {

        realm.beginTransaction();

        try {

            sharedPreferences.setUsername(username);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setPassword(String password) {

        realm.beginTransaction();

        try {

            sharedPreferences.setPassword(password);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setEmail(String email) {

        realm.beginTransaction();

        try {

            sharedPreferences.setEmail(email);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

    public void setRSAPublicKey(String RSAPublicKey){

        realm.beginTransaction();

        try {

            sharedPreferences.setKEY_RSA_PUBLIC_KEY(RSAPublicKey);

        }catch (Exception e){

            throw e;
        }

        realm.commitTransaction();
    }

}
