package Works;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import DataFaker.FakeShopItem;
import RealmObjects.Shop;
import RealmObjects.ShopItem;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnBitmapReceived;
import Server.Volley.interfaces.OnResponse;
import Storage.StorageBase;
import Utils.Consts;
import Utils.Converter;
import Utils.Downloader;
import Utils.log;
import io.realm.RealmList;

public class LoadShop{

    private static int shopVersion = 0;

    private int ShopLoadingProgress = 0;

    public void init(ProgressBar progressBar){

        log.print("Shop init :::: ");

        // check the shop version
        shopVersion = StorageBase.getInstance().getShopVersion();

        Volley.GET(Consts.ShopUrls.getVersion, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {

                log.print("LoadShop :: "+response.toString());


                int serverShopVersion = 0;
                try {
                    serverShopVersion = response.getInt("version");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                log.print("LoadShop :: serverShopVersion = "+serverShopVersion);

                // if db shop version is not equal
                if(shopVersion != serverShopVersion){

                    // get the shop object from the server
                    Volley.GET(Consts.ShopUrls.getShop, new OnResponse() {
                        @Override
                        public void onResponse(JSONObject response, int resultCode) {

                            log.print("LoadShop :: Updating the Shop :: "+response.toString());

                            int version;
                            String image;
                            String title;
                            JSONArray items;

                            try {

                                version = response.getInt("version");
                                image = response.getString("image");
                                title = response.getString("title");
                                items = response.getJSONArray("items");

                                Shop shop = new Shop();
                                shop.setVersion(version);
                                shop.setInfo(title);
                                shop.setPic_url(image);

                                RealmList<ShopItem> realmItems = new RealmList<>();

                                ShopItem shopItem = new ShopItem();

                                // for progress bar
                                int progress = Math.round(160 / items.length());

                                for(int i=0; i<items.length(); i++){

                                    JSONObject item = new JSONObject(items.get(i).toString());

                                    shopItem.setId(item.getInt("item_id"));
                                    shopItem.setTitle(item.getString("title"));
                                    shopItem.setInfo(item.getString("info"));
                                    shopItem.setPic_url(item.getString("image"));
                                    shopItem.setPrice(item.getInt("price"));
                                    shopItem.setLastPrice(item.getInt("lastprice"));

                                    realmItems.add(shopItem);

                                    progressBar.incrementProgressBy(progress);
                                    ShopLoadingProgress += progress;
                                }

                                progressBar.incrementProgressBy(10);
                                ShopLoadingProgress += 10;

                                shop.setShopItemList(realmItems);

                                progressBar.incrementProgressBy(10);
                                ShopLoadingProgress += 10;

                                StorageBase.getInstance().updateShop(shop);

                                progressBar.incrementProgressBy(10);
                                ShopLoadingProgress += 10;

                                downloadAndSaveImages(progressBar);

                            }catch (Exception e){

                                log.print("LoadShop :: error parsing the Shop object json");
                            }
                        }

                        @Override
                        public void onError(String error) {

                            log.print("LoadShop :: on updating shop ::: " + error);
                        }
                    });


                }else { // if db shop version is equal

                    while(ShopLoadingProgress < 200 ){

                        progressBar.incrementProgressBy(1);
                        ShopLoadingProgress += 1;
                    }
                    log.print("LoadShop :: version is the same");

                    checkSavedImages(progressBar);
                }

            }

            @Override
            public void onError(String error) {

                log.print("LoadShop :: couldn't get the shop version :: "+error);
            }
        });
    }

    private void downloadAndSaveImages(ProgressBar progressBar){

        Shop shop = StorageBase.getInstance().getShop();

        int progress = Math.round(220 / shop.getShopItemList().size());

        for (ShopItem item : shop.getShopItemList()){

            Volley.GetImage(item.getPic_url(), new OnBitmapReceived() {
                @Override
                public void onResponse(Bitmap response) {

                    String filePath = Consts.Dirs.ShopImagesFolder + item.getId() + ".png";

                    File img = new File(filePath);

                    Downloader.OnBitmapSaveListener listener = new Downloader.OnBitmapSaveListener() {
                        @Override
                        public void onBitmapSaved() {

                            log.test("Image saved ::: "+ filePath);

                            progressBar.incrementProgressBy(progress);
                            ShopLoadingProgress += progress;
                        }

                        @Override
                        public void onBitmapSaveError(Downloader.ImageError error) {

                            log.test("Image saving failed!!! ::: "+ filePath);
                        }
                    };

                    Downloader.writeToDisk(img, response, listener, Bitmap.CompressFormat.PNG, true);
                }

                @Override
                public void onError(String error) {

                    log.test("geting image from url failed !!! ::: "+error);
                }
            });
        }

        while(ShopLoadingProgress < 400 ){

            progressBar.incrementProgressBy(1);
            ShopLoadingProgress += 1;
        }
    }

    private void checkSavedImages(ProgressBar progressBar){

        Shop shop = StorageBase.getInstance().getShop();

        for(ShopItem item : shop.getShopItemList()){

            int progress = Math.round(200 / shop.getShopItemList().size());

            String filePath = Consts.Dirs.AppFilesFolder +"/"+ item.getId() + ".jpg";

            File imageFile = new File(filePath);

            if(!imageFile.exists()){

                log.test("image not exist !!! downloading the image :::" + filePath);

                Volley.GetImage(item.getPic_url(), new OnBitmapReceived() {
                    @Override
                    public void onResponse(Bitmap response) {

                        String filePath = Consts.Dirs.AppFilesFolder +"/"+ item.getId() + ".jpg";

                        File img = new File(filePath);

                        Downloader.OnBitmapSaveListener listener = new Downloader.OnBitmapSaveListener() {
                            @Override
                            public void onBitmapSaved() {

                                log.test("Image saved ::: " + filePath);
                            }

                            @Override
                            public void onBitmapSaveError(Downloader.ImageError error) {

                                log.test("Image saving failed!!! ::: " + filePath);
                            }
                        };

                        Downloader.writeToDisk(img, response, listener, Bitmap.CompressFormat.JPEG, true);

                        progressBar.incrementProgressBy(progress);
                        ShopLoadingProgress += progress;
                    }

                    @Override
                    public void onError(String error) {

                        log.test("geting image from url failed !!! ::: " + error);
                    }
                });

            }else{

                progressBar.incrementProgressBy(progress);
                log.print("Image file exist ::: "+filePath);
            }
        }

        while(ShopLoadingProgress < 400 ){

            progressBar.incrementProgressBy(1);
            ShopLoadingProgress += 1;
        }
    }
}