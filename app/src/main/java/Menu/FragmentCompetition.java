package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackcoin.packdel.bahmanproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCompetition extends Fragment {


    public FragmentCompetition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_competition, container, false);
    }

}
