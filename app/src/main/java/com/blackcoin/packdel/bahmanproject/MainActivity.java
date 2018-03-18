package com.blackcoin.packdel.bahmanproject;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import SplashScreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    public static void log(String s){Log.i("Bahman!",s);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Show The Splash Screen
        new SplashScreen(getSupportFragmentManager()).show();

    }
}
