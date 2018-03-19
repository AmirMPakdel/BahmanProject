package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.blackcoin.packdel.bahmanproject.R;

import Archives.*;


public class FragmentArchives extends Fragment {


    public FragmentArchives() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_menu_archives, container, false);

        // Setup the Archives
        new Archives(view, getActivity().getSupportFragmentManager()).setup();

        return view;
    }

}
