package com.blackcoin.packdel.bahmanproject;


import android.app.Application;

import Security.RSA;
import Server.Server;
import Server.Volley.VolleySingleton;
import Storage.StorageBase;
import Storage.StorageBox;
import Utils.log;
import io.realm.Realm;

public class StartUpManager extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        StorageBox.init();

        log.print("StartUP Manger started!");

        log.print("First time Run : "+StorageBox.sharedPreferences.isFirstTimeRun());

        StorageBase.init();

        VolleySingleton.init(getApplicationContext());

        if(!RSA.init(getApplicationContext()))// if there is no RSA Public Key Available
        {
            RSA.Is_Public_Key_Available = false;
            RSA.loadKey();
            // TODO: 4/13/18  call the server in Loading Activity
        }


        Server.ServerTest();

        log.print("StartUp Manager Finished!");

    }
}
