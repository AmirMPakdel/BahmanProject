package com.blackcoin.packdel.bahmanproject;


import android.app.Application;
import android.content.res.Resources;

import Security.RSA;
import Server.Server;
import Server.SocketIO;
import Server.Volley.VolleySingleton;
import Storage.StorageBase;
import Storage.StorageBox;
import Utils.log;
import io.realm.Realm;

public class StartUpManager extends Application
{

    public static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        log.print("StartUP Manger started!");

        Realm.init(getApplicationContext());

        StorageBox.init();

        log.print("First time Run : "+StorageBox.sharedPreferences.isFirstTimeRun());

        StorageBase.init();

        SocketIO.init();

        VolleySingleton.init(getApplicationContext());

        if(!RSA.init(getApplicationContext()))// if there is no RSA Public Key Available
        {
            RSA.Is_Public_Key_Available = false;
            RSA.loadKey();
            // TODO: 4/13/18  call the server in Loading Activity
        }

        Server.ServerTest(getApplicationContext());

        resources = getResources();

        log.print("StartUp Manager Finished!");

    }
}
