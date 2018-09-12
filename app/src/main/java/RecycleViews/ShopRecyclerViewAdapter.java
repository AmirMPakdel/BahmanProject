package RecycleViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import Animation.ClickAnimation;
import RealmObjects.ShopItem;
import Utils.Font;
import Utils.log;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder> {

    private List<ShopItem> shopItemList;
    private Context context;

    public ShopRecyclerViewAdapter(Context context, List<ShopItem> shopItems){

        this.context = context;
        this.shopItemList = shopItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_shopitem, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.title.setTypeface(Font.myFont);
        viewHolder.info.setTypeface(Font.myFont);
        viewHolder.price.setTypeface(Font.myFont);
        //viewHolder.lastPrice.setTypeface(Font.myFont);
        viewHolder.moneySign.setTypeface(Font.myFont);

        viewHolder.title.setText(shopItemList.get(i).getTitle());
        viewHolder.info.setText(shopItemList.get(i).getInfo());
        viewHolder.price.setText(String.valueOf(shopItemList.get(i).getPrice()));
        //viewHolder.lastPrice.setText(String.valueOf(shopItemList.get(i).getLastPrice()));

        viewHolder.pic.setImageResource(R.drawable.android_logo);
        //TODO::download the image from url
        String url = shopItemList.get(i).getPic_url();

        viewHolder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //TODO:: open a buying dialog
                int item_id = shopItemList.get(i).getId();
            }
        });
    }

    @Override
    public int getItemCount() {

        return shopItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout container;
        TextView title, info, moneySign, price, lastPrice;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.item_container);
            title = itemView.findViewById(R.id.item_title);
            info = itemView.findViewById(R.id.item_info);
            moneySign = itemView.findViewById(R.id.item_money_sign);
            price = itemView.findViewById(R.id.item_price);
            //lastPrice = itemView.findViewById(R.id.item_last_price);
            pic = itemView.findViewById(R.id.item_image);
        }
    }
}
