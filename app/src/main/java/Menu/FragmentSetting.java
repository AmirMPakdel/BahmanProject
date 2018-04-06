package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;


public class FragmentSetting extends Fragment {


    public FragmentSetting() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_setting, container, false);

        // set the title
        TextView title = view.findViewById(R.id.toolbar_title);
        title.setText("تنظیمات");
        title.setTypeface(MainActivity.myFont);

        return view;
    }

}
