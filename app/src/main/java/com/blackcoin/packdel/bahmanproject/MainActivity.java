package com.blackcoin.packdel.bahmanproject;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import Menu.FragmentArchives;
import Menu.FragmentCompetition;
import Menu.FragmentHome;
import Menu.FragmentSetting;
import Menu.FragmentShop;
import Menu.ViewPagerAdapter;
import SplashScreen.SplashScreen;
import Toolbar.MenuToolbar;

public class MainActivity extends AppCompatActivity {

    public static void log(String s){Log.i("Bahman!",s);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen the Activity then set the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Show The Splash Screen
        new SplashScreen(getSupportFragmentManager()).show();

        // Setup MenuToolbar
        new MenuToolbar(findViewById(R.id.relativeLayout), getSupportFragmentManager()).setup();

    }
}
