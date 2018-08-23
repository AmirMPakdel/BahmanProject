package com.blackcoin.packdel.bahmanproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import Gameplay.FragmentChat;
import Gameplay.FragmentFieldChoosing;
import Gameplay.FragmentRounds;
import RealmObjects.Match;
import Storage.StorageBase;
import Utils.Consts;
import Utils.log;

public class QuickGameActivity extends AppCompatActivity {


    public Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region MainCodes

        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quick_game);

        String contest_id = getIntent().getExtras().getString("Contest_id");
        match = StorageBase.getInstance().getMatch(contest_id);
        //endregion


        // check contest's state

        switch (match.getState()){

            case Consts.Match.STATE_ANSWERING:

                FragmentRounds fragmentRounds = new FragmentRounds();

                getSupportFragmentManager().beginTransaction().add(R.id.quick_match_frame, fragmentRounds).commit();

                break;

            case Consts.Match.STATE_BOOK_CHOOSING:

                FragmentFieldChoosing fragmentFieldChoosing = new FragmentFieldChoosing();

                getSupportFragmentManager().beginTransaction().add(R.id.quick_match_frame, fragmentFieldChoosing);

                break;

            default:

                //TODO: ge the match fixed by syncing it with server
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }
}
