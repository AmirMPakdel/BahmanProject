package com.blackcoin.packdel.bahmanproject;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import Server.Server;
import SplashScreen.SplashScreen;
import Toolbar.MenuToolbar;

public class MainActivity extends AppCompatActivity {

    public static Typeface myFont;

    public static void log(String s){Log.i("Bahman!",s);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Set the Font
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/zak.ttf");

        // Show The Splash Screen
        new SplashScreen(getSupportFragmentManager()).show();

        // Setup MenuToolbar
        new MenuToolbar(findViewById(R.id.relativeLayout), getSupportFragmentManager()).setup();

        // test ground

        Server server = new Server(null);

        server.signIn(this);

    }
}
