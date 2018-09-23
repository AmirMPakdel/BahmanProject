package Menu;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import Animation.ShopToolbarAnimation;
import RecycleViews.ShopRecyclerView;
import Utils.Font;


public class FragmentShop extends Fragment {


    public FragmentShop() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_shop, container, false);

        // set the title
        TextView title = view.findViewById(R.id.toolbar_title);
        title.setText("فروشگاه");
        title.setTypeface(Font.myFont);
        TextView shopInfo = view.findViewById(R.id.shop_title);
        shopInfo.setTypeface(Font.myFont);

        // set the recycler view
        ShopRecyclerView shopRecyclerView = new ShopRecyclerView(getContext(),view);
        shopRecyclerView.setup();

        //shop toolbar animation
        ConstraintLayout toolbar = view.findViewById(R.id.shopToolbar);
        ShopToolbarAnimation.animate(toolbar, getResources());

        return view;
    }
}
