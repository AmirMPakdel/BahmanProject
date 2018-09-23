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

import RealmObjects.Match;
import RealmObjects.Round;
import Storage.StorageBase;
import Utils.Consts;
import Utils.Converter;
import Views.RoundedCornerLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmList;

public class FragmentRounds extends Fragment {


    public FragmentRounds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rounds, container, false);

        Match match = StorageBase.getInstance().getMatch(QuickGameActivity.match.getId());

        // init match to ui
        update_ui(match, view);

        // TODO:: ARG :: write a listener which gives the servers data of match's round result


        return view;
    }

    private void update_ui(Match match, View view){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

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

                my_name.setText(match.getMyName());

                opponent_name.setText(match.getOpponentName());

                my_pic.setImageBitmap(Converter.byteArray2Bitmap(match.getMyPic()));

                opponent_pic.setImageBitmap(Converter.byteArray2Bitmap(match.getOpponentPic()));


                RealmList<Round> rounds = match.getRoundsList();

                Round round1 = rounds.get(0);
                Round round2 = rounds.get(1);
                Round round3 = rounds.get(2);
                Round round4 = rounds.get(3);

                //
                round1_book.setText(round1.getBook());
                setTestLightColor(r1_my_1, round1.getMy_1_test());
                setTestLightColor(r1_my_2, round1.getMy_2_test());
                setTestLightColor(r1_op_1, round1.getOp_1_test());
                setTestLightColor(r1_op_2, round1.getOp_2_test());

                //
                round2_book.setText(round2.getBook());
                setTestLightColor(r2_my_1, round1.getMy_1_test());
                setTestLightColor(r2_my_2, round1.getMy_2_test());
                setTestLightColor(r2_op_1, round1.getOp_1_test());
                setTestLightColor(r2_op_2, round1.getOp_2_test());

                //
                round3_book.setText(round3.getBook());
                setTestLightColor(r3_my_1, round1.getMy_1_test());
                setTestLightColor(r3_my_2, round1.getMy_2_test());
                setTestLightColor(r3_op_1, round1.getOp_1_test());
                setTestLightColor(r3_op_2, round1.getOp_2_test());

                //
                round4_book.setText(round4.getBook());
                setTestLightColor(r4_my_1, round1.getMy_1_test());
                setTestLightColor(r4_my_2, round1.getMy_2_test());
                setTestLightColor(r4_op_1, round1.getOp_1_test());
                setTestLightColor(r4_op_2, round1.getOp_2_test());
            }
        });

    }

    private int roundAnswer2TestLightColor(int answer){

        switch (answer){

            case Consts.Test.TEST_CORRECT_ANSWER:
                return Consts.Test.TEST_CORRECT_ANSWER_COLOR;

            case Consts.Test.TEST_NOT_ANSWERED:
                return Consts.Test.TEST_NOT_ANSWERED_COLOR;

            case Consts.Test.TEST_WRONG_ANSWER:
                return Consts.Test.TEST_WRONG_ANSWER_COLOR;

        }

        return 0;
    }

    private void setTestLightColor(RoundedCornerLayout testlight, int answer){

        testlight.setBackgroundColor(roundAnswer2TestLightColor(answer));
    }

}
