package Storage;

import com.blackcoin.packdel.bahmanproject.StartUpManager;

import java.util.ArrayList;
import java.util.List;


import Models.Book;
import Models.Field;
import RealmObjects.Chest;
import RealmObjects.Match;
import RealmObjects.Round;
import RealmObjects.Shop;
import RealmObjects.ShopItem;
import Utils.Consts;
import Utils.log;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class StorageBase {

    private Realm realm;

    private static StorageBase storageBase;

    private StorageBase(){

        realm = Database.getRealm();
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

            chestList.add(new Chest(Book.Ensani.EGHTESAD));
            chestList.add(new Chest(Book.Ensani.EJTEMAIE));
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

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(chestList);
        realm.commitTransaction();
    }

    public List<Chest> getChestsList(){

        RealmResults<Chest> results = realm.where(Chest.class).findAll();

        List<Chest> chestList = new ArrayList<>();

        for(Chest chest : results){

            chestList.add(chest);
        }

        return results;
    }

    public List<Match> getRunningMatchList(){

        List<Match> matches = realm.where(Match.class).notEqualTo("state", Consts.Match.STATE_FINISHED).findAll();

        return matches;
    }

    public void createMatch(Match match){

        realm.beginTransaction();

        for (int i=1; i<= 4; i++){

            Round round = new Round();

            round.setNumber(i);

            match.roundsList.add(round);
        }
        realm.copyToRealmOrUpdate(match);
        realm.commitTransaction();
    }

    public void setMatch_rounds(String id){

        Match myMatch = getMatch(id);
        realm.beginTransaction();
        myMatch.getRoundsList().get(0).setBook(StartUpManager.resources.getString(Book.setFieldString((Book.Riazi.FIZIK))));
        myMatch.getRoundsList().get(1).setBook(StartUpManager.resources.getString(Book.setFieldString((Book.Omoumi.DINI))));
        myMatch.getRoundsList().get(2).setBook(StartUpManager.resources.getString(Book.setFieldString((Book.Riazi.SHIMI))));
        myMatch.getRoundsList().get(3).setBook(StartUpManager.resources.getString(Book.setFieldString((Book.Omoumi.ADABIAT))));

        realm.commitTransaction();
    }

    public Match getMatch(String id) {

        return realm.where(Match.class).equalTo("id",id).findFirst();
    }



    //region shop
    public void createShop(){

        RealmResults<Shop> shops = realm.where(Shop.class).findAll();

        if(shops.size() > 1){

            throw new NullPointerException("there should be shop in database !!! :: number:"+shops.size());

        } else if(shops.size() == 0){

            Shop shop = new Shop();
            realm.beginTransaction();
            realm.copyToRealm(shop);
            realm.commitTransaction();

            log.print("shop has been created :D");

        }else{

            log.print("shop is ready :)");
        }
    }

    public Shop getShop(){

        return realm.where(Shop.class).findFirst();
    }

    public int getShopVersion(){

        return this.getShop().getVersion();
    }

    public void updateShop(Shop shop) {

        realm.beginTransaction();
        realm.where(Shop.class).findAll().deleteAllFromRealm();
        realm.copyToRealm(shop);
        realm.commitTransaction();
    }

    public void updateShopItemList(RealmList<ShopItem> shopItems){

        realm.beginTransaction();
        getShop().setShopItemList(shopItems);
        realm.commitTransaction();
    }
    //endregion
}
