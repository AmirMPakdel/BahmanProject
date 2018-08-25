package Gameplay;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.QuickGameActivity;
import com.blackcoin.packdel.bahmanproject.R;

import Views.RoundedCornerLayout;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentRounds extends Fragment {


    public FragmentRounds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rounds, container, false);

        //region finding views
        CircleImageView opponent_pic = view.findViewById(R.id.opponent_pic);

        CircleImageView my_pic = view.findViewById(R.id.my_pic);

        TextView opponent_name = view.findViewById(R.id.opponent_name_tv);

        TextView my_name = view.findViewById(R.id.my_name_tv);

        ///// round 1

        TextView round1_book = view.findViewById(R.id.round_1_book);

        RoundedCornerLayout r1_my_1 = view.findViewById(R.id.round_1_test_my_1);

        RoundedCornerLayout r1_my_2 = view.findViewById(R.id.round_1_test_my_2);

        RoundedCornerLayout r1_op_1 = view.findViewById(R.id.round_1_test_op_1);

        RoundedCornerLayout r1_op_2 = view.findViewById(R.id.round_1_test_op_2);

        ///// round 2

        TextView round2_book = view.findViewById(R.id.round_2_book);

        RoundedCornerLayout r2_my_1 = view.findViewById(R.id.round_2_test_my_1);

        RoundedCornerLayout r2_my_2 = view.findViewById(R.id.round_2_test_my_2);

        RoundedCornerLayout r2_op_1 = view.findViewById(R.id.round_2_test_op_1);

        RoundedCornerLayout r2_op_2 = view.findViewById(R.id.round_2_test_op_2);

        ////// round 3

        TextView round3_book = view.findViewById(R.id.round_3_book);

        RoundedCornerLayout r3_my_1 = view.findViewById(R.id.round_3_test_my_1);

        RoundedCornerLayout r3_my_2 = view.findViewById(R.id.round_3_test_my_2);

        RoundedCornerLayout r3_op_1 = view.findViewById(R.id.round_3_test_op_1);

        RoundedCornerLayout r3_op_2 = view.findViewById(R.id.round_3_test_op_2);

        ////// round 4

        TextView round4_book = view.findViewById(R.id.round_4_book);

        RoundedCornerLayout r4_my_1 = view.findViewById(R.id.round_4_test_my_1);

        RoundedCornerLayout r4_my_2 = view.findViewById(R.id.round_4_test_my_2);

        RoundedCornerLayout r4_op_1 = view.findViewById(R.id.round_4_test_op_1);

        RoundedCornerLayout r4_op_2 = view.findViewById(R.id.round_4_test_op_2);
        //endregion

        return view;
    }

}
