package DataFaker;

import java.util.List;

import RealmObjects.Shop;
import RealmObjects.ShopItem;
import Storage.Database;
import Storage.StorageBase;
import Storage.StorageBox;
import io.realm.Realm;
import io.realm.RealmList;

public class FakeShopItem {

    public static void createFakeShopItem(int number){

        RealmList<ShopItem> shopItems = new RealmList<>();

        Shop shop = StorageBase.getInstance().getShop();

        Realm realm = Database.getRealm();

        realm.beginTransaction();
        realm.where(ShopItem.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();

        for (int i=2010; i<2030; i++) {

            realm.beginTransaction();
            ShopItem  shopItem = realm.createObject(ShopItem.class);
            shopItem.setId(i);
            shopItem.setTitle("یه کیسه انرژی");
            shopItem.setLastPrice(4000);
            shopItem.setPrice(2000);
            shopItem.setPic_url("...");
            shopItem.setInfo("یه کیسه با 100 واحد انرژی");

            shop.getShopItemList().add(shopItem);

            realm.commitTransaction();
        }



        //StorageBase.getInstance().updateShopItemList(shopItems);

    }
}
