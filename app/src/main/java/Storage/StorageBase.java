package Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import Models.Book;
import Models.Field;
import RealmObjects.Chest;
import Utils.Consts;
import io.realm.Realm;

public class StorageBase {

    private Realm realm;

    private static StorageBase storageBase;

    private StorageBase(){

        realm = Database.getRealm();

        if(StorageBox.sharedPreferences.isFirstTimeRun()){

            CreateChests();
        }
    }

    public static synchronized StorageBase init(){

        if(StorageBox.getInstance() == null){

            throw new NullPointerException("Before initializing StorageBase , StorageBox Must be initialized first => (call StorageBox.init(); )");
        }

        if(storageBase == null){

            storageBase = new StorageBase();
        }

        return storageBase;
    }

    public static synchronized StorageBase getInstance(){

        if(storageBase == null){

            throw new NullPointerException("StorageBase Must be initialized first => (call StorageBase.init(); )");
        }

        return storageBase;
    }

    private List<Chest> getChestList(String field) {

        List<Chest> chestList = new ArrayList<>();

        chestList.add(new Chest(Book.Omoumi.ADABIAT));
        chestList.add(new Chest(Book.Omoumi.DINI));
        chestList.add(new Chest(Book.Omoumi.ARABI));
        chestList.add(new Chest(Book.Omoumi.ENGELISI));

        if(field.equals(Field.RIAZI_FIELD)){

            chestList.add(new Chest(Book.Riazi.RIAZI));
            chestList.add(new Chest(Book.Riazi.FIZIK));
            chestList.add(new Chest(Book.Riazi.SHIMI));
            return chestList;

        }else if (field.equals(Field.TAJROBI_FIELD)){

            chestList.add(new Chest(Book.Tajrobi.ZAMINSHENASI));
            chestList.add(new Chest(Book.Tajrobi.RIAZI));
            chestList.add(new Chest(Book.Tajrobi.ZISTSHENASI));
            chestList.add(new Chest(Book.Tajrobi.FIZIK));
            chestList.add(new Chest(Book.Tajrobi.SHIMI));
            return chestList;

        }else if (field.equals(Field.ENSANI_FIELD)){

            chestList.add(new Chest(Book.Ensani.ADABIAT));
            chestList.add(new Chest(Book.Ensani.ARABI));
            chestList.add(new Chest(Book.Ensani.EGHTESAD));
            chestList.add(new Chest(Book.Ensani.Ejtemaie));
            chestList.add(new Chest(Book.Ensani.FALSAFE));
            chestList.add(new Chest(Book.Ensani.JOGHRAFIA));
            chestList.add(new Chest(Book.Ensani.MANTEGH));
            chestList.add(new Chest(Book.Ensani.RAVANSHENASI));
            chestList.add(new Chest(Book.Ensani.RIAZI));
            chestList.add(new Chest(Book.Ensani.TARIKH));
            return chestList;

        }else if (field.equals(Field.HONAR_FIELD)){

            chestList.add(new Chest(Book.Honar.DARKEHONAR));
            chestList.add(new Chest(Book.Honar.KHALAGHIATEMOSIGHI));
            chestList.add(new Chest(Book.Honar.KHALAGHIATENAMAYESHI));
            chestList.add(new Chest(Book.Honar.KHALAGHIATETASVIRI));
            chestList.add(new Chest(Book.Honar.KHAVASEMAVAD));
            chestList.add(new Chest(Book.Honar.RIAZIFIZIK));
            chestList.add(new Chest(Book.Honar.TARSIMEFANI));
            return chestList;
        }

        return null;
    }

    public void CreateChests(){


        List<Chest> chestList = getChestList(StorageBox.sharedPreferences.getField());

        realm.copyToRealm(chestList);

        //List<String> bookList = new Book().getBooksList(StorageBox.sharedPreferences.getField());
        /*for(int i=0; i<bookList.size(); i++){

            realm.copyToRealm(new Book())

            db.execSQL("INSERT INTO chests(field, capacity, load) VALUES('"+bookList.get(i)+"', "+ Consts.NEW_CHEST_CAPACITY +", 0)");
        }*/
    }


}
