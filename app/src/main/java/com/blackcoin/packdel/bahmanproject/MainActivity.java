package com.blackcoin.packdel.bahmanproject;


import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;

import Server.SocketIO;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnBitmapReceived;
import SplashScreen.SplashScreen;
import Storage.Database;
import Utils.Converter;
import Utils.Downloader;
import Utils.Font;
import Utils.log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region MainCodes

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Set the Font
        Font.myFont = Typeface.createFromAsset(this.getAssets(), "fonts/ampsans.ttf");

        // Splash Screen and Loading data
        new SplashScreen(getSupportFragmentManager()).show();
        //endregion

        //region test codes

        //endregion

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // release All resources
        SocketIO.getInstance().release();
        Database.getRealm().close();
    }
}
