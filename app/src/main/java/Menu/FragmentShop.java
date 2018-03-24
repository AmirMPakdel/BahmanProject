package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;


public class FragmentShop extends Fragment {


    public FragmentShop() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_shop, container, false);

        // set the title
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        title.setText("فروشگاه");
        title.setTypeface(MainActivity.myFont);

        return view;
    }

}
