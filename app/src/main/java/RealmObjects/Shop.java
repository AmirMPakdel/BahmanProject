package RealmObjects;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Shop extends RealmObject {

    private int version = 1;

    private String pic_url;

    private String info;

    private RealmList<ShopItem> shopItemList = new RealmList<>();

    public Shop() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public RealmList<ShopItem> getShopItemList() {
        return shopItemList;
    }

    public void setShopItemList(RealmList<ShopItem> shopItemList) {
        this.shopItemList = shopItemList;
    }
}
