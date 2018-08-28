package Gameplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.QuickGameActivity;
import com.blackcoin.packdel.bahmanproject.R;

import RealmObjects.Match;
import Server.OnMatchUpdate;
import Server.SocketIO;
import Storage.StorageBase;
import Utils.Converter;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentBookChoosing extends Fragment {


    public FragmentBookChoosing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_book_choosing, container, false);

        Match match = StorageBase.getInstance().getMatch(QuickGameActivity.match.getId());

        update_ui(match, view);

        // TODO:: ARG :: write a listener which gives the servers data of match's book choosing result

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

                TextView opponent_fist_book = view.findViewById(R.id.opponent_first_book);

                TextView opponent_second_book = view.findViewById(R.id.opponent_second_book);

                TextView my_first_book = view.findViewById(R.id.my_first_book);

                TextView my_second_book = view.findViewById(R.id.my_second_book);

                RadioGroup radioGroup = view.findViewById(R.id.books_radio_group);
                //endregion

                my_name.setText(match.getMyName());
                opponent_name.setText(match.getOpponentName());
                my_pic.setImageBitmap(Converter.byteArray2Bitmap(match.getMyPic()));
                opponent_pic.setImageBitmap(Converter.byteArray2Bitmap(match.getOpponentPic()));


                my_first_book.setText(match.getMy_1_book());

                my_second_book.setText(match.getMy_2_book());

                opponent_fist_book.setText(match.getOpponent_1_book());

                opponent_second_book.setText(match.getOpponent_2_book());


            }
        });

    }

}
