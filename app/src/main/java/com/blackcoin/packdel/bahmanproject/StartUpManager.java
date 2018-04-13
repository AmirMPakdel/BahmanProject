package com.blackcoin.packdel.bahmanproject;


import android.app.Application;

import Security.RSA;
import Server.Volley.VolleySingleton;
import Utils.log;

public class StartUpManager extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleySingleton.init(getApplicationContext());


        if(!RSA.init(getApplicationContext()))// if there is no RSA Public Key Available
        {
            RSA.Is_Public_Key_Available = false;
            // TODO: 4/13/18  call the server in Loading Activity
        }


        log.print("StartUpManager Finished");

    }
}
