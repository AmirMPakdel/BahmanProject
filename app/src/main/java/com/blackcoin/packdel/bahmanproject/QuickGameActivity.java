package com.blackcoin.packdel.bahmanproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import QucikGame.ScoreboardFragment;

public class QuickGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_game);

        FrameLayout frameLayout = findViewById(R.id.quick_match_frame);

        getSupportFragmentManager().beginTransaction().add(R.id.quick_match_frame, new ScoreboardFragment()).commit();


    }
}
