package com.blackcoin.packdel.bahmanproject;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import SplashScreen.SplashScreen;
import Utils.Font;

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
        Font.myFont = Typeface.createFromAsset(this.getAssets(), "fonts/zak.ttf");

        /* Show The Splash Screen
         * Many important Things happen in SplashScreen
         */
        new SplashScreen(getSupportFragmentManager()).show();
        //endregion

        //region test codes

        /*
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "ali97");
            jsonObject.put("password", "ali123456");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Volley.POST_Encrypted(Consts.Registration_signin, jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode)
            {
                log.print(String.valueOf(resultCode));
                log.print(response.toString());
            }

            @Override
            public void onError(String error)
            {
                log.print("Error : " + error);
            }
        });

        try {

            jsonObject.put("username", "amir97");
            jsonObject.put("password", "ali123456");
        }
        catch (Exception e){}


        log.print("################################");


        Volley.POST_Encrypted("http://172.16.205.19:8000/api/user/login/", jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode)
            {
                log.print(String.valueOf(resultCode));
                log.print(response.toString());
            }

            @Override
            public void onError(String error)
            {
                log.print("Error : " + error);
            }
        });
        //endregion
        */
    }
}
