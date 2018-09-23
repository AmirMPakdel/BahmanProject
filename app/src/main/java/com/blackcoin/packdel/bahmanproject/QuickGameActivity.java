package com.blackcoin.packdel.bahmanproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedInputStream;
import java.io.InputStream;

import Gameplay.FragmentBookChoosing;
import Gameplay.FragmentRounds;
import Models.Book;
import RealmObjects.Match;
import Storage.StorageBase;
import Utils.Consts;
import Utils.Converter;
import Utils.log;
import io.realm.RealmList;

public class QuickGameActivity extends AppCompatActivity {


    public static Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //region MainCodes

        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quick_game);

        String match_id = getIntent().getExtras().getString("match_id");
        //endregion

        //region test
        Match myMatch = new Match();

        try {

            InputStream inputStream1 = getAssets().open("mypic.jpg");

            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(inputStream1);

            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream1);

            byte[] my_pic = Converter.Bitmap2ByteArray(bitmap);

            myMatch.setMyPic(my_pic);

            InputStream inputStream2 = getAssets().open("oppic.png");

            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);

            bitmap = BitmapFactory.decodeStream(bufferedInputStream2);

            byte[] op_pic = Converter.Bitmap2ByteArray(bitmap);

            myMatch.setOpponentPic(op_pic);

        }catch (Exception e){

            log.print("error :::: "+e.getMessage());
        }

        myMatch.setId("1");
        myMatch.setMyName("امیرعلی");
        myMatch.setOpponentName("امیرحسین");

        RealmList<String> books_1 = new RealmList<>();
        books_1.add(Book.Omoumi.ADABIAT);
        books_1.add(Book.Omoumi.DINI);
        books_1.add(Book.Omoumi.ENGELISI);

        myMatch.setBook_1_choices(books_1);

        RealmList<String> books_2 = new RealmList<>();
        books_2.add(Book.Riazi.FIZIK);
        books_2.add(Book.Riazi.RIAZI);
        books_2.add(Book.Riazi.SHIMI);

        myMatch.setBook_2_choices(books_2);

        StorageBase.getInstance().createMatch(myMatch);
        StorageBase.getInstance().setMatch_rounds("1");


        //endregion

        match = StorageBase.getInstance().getMatch("1");

        //region check match's state

        switch (/*match.getState()*/Consts.Match.STATE_BOOK_CHOOSING){

            case Consts.Match.STATE_ANSWERING:

                FragmentRounds fragmentRounds = new FragmentRounds();

                getSupportFragmentManager().beginTransaction().add(R.id.quick_match_frame, fragmentRounds).commit();

                break;

            case Consts.Match.STATE_BOOK_CHOOSING:

                FragmentBookChoosing fragmentBookChoosing = new FragmentBookChoosing();

                getSupportFragmentManager().beginTransaction().add(R.id.quick_match_frame, fragmentBookChoosing).commit();

                break;

            default:

                //TODO: ge the match fixed by syncing it with server
                break;
        }
        //endregion
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
