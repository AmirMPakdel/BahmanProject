package Gameplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentFieldChoosing extends Fragment {


    public FragmentFieldChoosing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_field_choosing, container, false);

        //region finding views
        CircleImageView opponent_pic = view.findViewById(R.id.opponent_pic);

        CircleImageView my_pic = view.findViewById(R.id.my_pic);

        TextView opponent_name = view.findViewById(R.id.opponent_name_tv);

        TextView my_name = view.findViewById(R.id.my_name_tv);

        TextView opponent_fist_book = view.findViewById(R.id.opponent_first_book);

        TextView opponent_second_book = view.findViewById(R.id.opponent_second_book);

        TextView my_first_book = view.findViewById(R.id.my_first_book);

        TextView my_second_book = view.findViewById(R.id.my_second_book);

        RadioGroup radioGroup = view.findViewById(R.id.books_radio_group);
        //endregion

        return view;

    }

}
