package RecycleViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import DataFaker.FakeShopItem;
import RealmObjects.ShopItem;
import Storage.StorageBase;


public class ShopRecyclerView {

    private Context context;
    private View view;

    public ShopRecyclerView(Context context, View view){

        this.context = context;

        this.view = view;

        FakeShopItem.createFakeShopItem(12);
    }

    public void setup(){

        List<ShopItem> shopItemList = StorageBase.getInstance().getShop().getShopItemList();

        RecyclerView recyclerView = view.findViewById(R.id.shop_recyclerView);

        recyclerView.setAdapter(new ShopRecyclerViewAdapter(this.context, shopItemList));

        recyclerView.setLayoutManager(new GridLayoutManager(this.context,2));
    }
}
