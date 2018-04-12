package com.blackcoin.packdel.bahmanproject;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import Security.Cryptography;
import Server.Server;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import SplashScreen.SplashScreen;
import Storage.StorageBox;
import Storage.StorageLite;

public class MainActivity extends AppCompatActivity {

    public static Typeface myFont;

    public static StorageBox storageBox;

    public static StorageLite storageLite;

    public static void log(String s) {
        Log.i("Bahman!", s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Set the Font
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/zak.ttf");

        // Set the StorageBox
        storageBox = new StorageBox(getSharedPreferences(StorageBox.SHARED_PREFERENCES_NAME, MODE_PRIVATE));

        // Set the StorageLite
        storageLite = new StorageLite(this);

        /* Show The Splash Screen
         * Many important Things happen in SplashScreen
         */
        new SplashScreen(getSupportFragmentManager()).show();

        // test ground

        ///Server server = new Server(null);

        //server.signIn(this);

        String st = "salam2647rلعنت بر ایران28hy9090@#$%^&*";
        String en = Cryptography.encrypt(st);
        MainActivity.log("en :"+en);

        String de = Cryptography.decrypt(en);
        MainActivity.log("de :"+de);

        if(de.equals(st)){
            MainActivity.log("Cool :)");
        }


    }
}
