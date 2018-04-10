package com.blackcoin.packdel.bahmanproject;

import android.app.Application;

import Server.Volley.VolleySingleton;

public class StartUpManager extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleySingleton.init(getApplicationContext());
        MainActivity.log("StartUpManager");

    }
}
