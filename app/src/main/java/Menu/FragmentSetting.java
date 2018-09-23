package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import Dialogs.RegistrationDialog;
import Utils.Font;


public class FragmentSetting extends Fragment {


    public FragmentSetting() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_setting, container, false);

        TextView registration_txt = view.findViewById(R.id.registration_txt);
        registration_txt.setTypeface(Font.myFont);

        FrameLayout registration_btn = view.findViewById(R.id.registration_btn);
        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RegistrationDialog(getContext()).setup();
            }
        });

        return view;
    }

}
