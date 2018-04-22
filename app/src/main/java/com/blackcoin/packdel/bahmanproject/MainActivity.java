package com.blackcoin.packdel.bahmanproject;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import Security.Cryptography;
import Security.KeyAndIV;
import Security.RSA;
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
        Log.i("FuckThisShit!", s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region MainCodes

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Set the Font
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/zak.ttf");

        // Set the StorageBox
        storageBox = new StorageBox(getApplicationContext());

        // Set the StorageLite
        storageLite = new StorageLite(this);

        /* Show The Splash Screen
         * Many important Things happen in SplashScreen
         */
        new SplashScreen(getSupportFragmentManager()).show();

        // test ground

        ///Server server = new Server(null);

        //server.signIn(this);

        //endregion


        //region test codes

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "ali97");
            jsonObject.put("password", "ali123456");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Volley.POST_Encrypted("http://172.16.205.19:8000/api/user/login/", jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode)
            {
                log(String.valueOf(resultCode));
                log(response.toString());
            }

            @Override
            public void onError(String error)
            {
                log("Error : " + error);
            }
        });

        try {

            jsonObject.put("username", "amir97");
            jsonObject.put("password", "ali123456");
        }
        catch (Exception e){}


        log("################################");


        Volley.POST_Encrypted("http://172.16.205.19:8000/api/user/login/", jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode)
            {
                log(String.valueOf(resultCode));
                log(response.toString());
            }

            @Override
            public void onError(String error)
            {
                log("Error : " + error);
            }
        });


        //endregion


    }
}
