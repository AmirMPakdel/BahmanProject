package com.blackcoin.packdel.bahmanproject;


import android.app.Application;
import android.content.res.Resources;

import Utils.Consts;
import Security.RSA;
import Server.Server;
import Server.Volley.VolleySingleton;
import Storage.StorageBase;
import Storage.StorageBox;
import Utils.log;
import io.realm.Realm;

public class StartUpManager extends Application {

    public static Resources resources;

    @Override
    public void onCreate() {

        super.onCreate();

        Consts.Dirs.AppFilesFolder = getExternalCacheDir().getAbsolutePath();

        resources = getResources();
    }
}
