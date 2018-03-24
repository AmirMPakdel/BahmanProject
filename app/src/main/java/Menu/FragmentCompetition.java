package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;


public class FragmentCompetition extends Fragment {


    public FragmentCompetition() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_competition, container, false);

        // set the title
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        title.setText("فعالیت های من");
        title.setTypeface(MainActivity.myFont);

        return view;
    }

}
