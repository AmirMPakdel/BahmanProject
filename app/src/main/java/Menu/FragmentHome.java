package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blackcoin.packdel.bahmanproject.R;

import Dialogs.RegistrationDialog;


public class FragmentHome extends Fragment {


    public FragmentHome() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

        // test ground

        Button Registration_btn = view.findViewById(R.id.registration_btn);

        Registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RegistrationDialog(getContext()).setup();

            }
        });


        return view;
    }

}
