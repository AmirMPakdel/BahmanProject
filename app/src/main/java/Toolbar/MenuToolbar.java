package Toolbar;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blackcoin.packdel.bahmanproject.R;

import Animation.ClickAnimation;
import Animation.ToolbarAnimation;
import Menu.FragmentArchives;
import Menu.FragmentCompetition;
import Menu.FragmentHome;
import Menu.FragmentSetting;
import Menu.FragmentShop;
import Menu.ViewPagerAdapter;
import Storage.StorageBox;

public class MenuToolbar {

    private View view;

    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private boolean start = true;

    public MenuToolbar(View view, FragmentManager SupportFragmentManager) {

        this.view = view;
        viewPager = view.findViewById(R.id.home_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(SupportFragmentManager);
    }


    public void setup(){

        // TabLayout Animation
        if(start){
            RelativeLayout RelLayout = view.findViewById(R.id.bottom_toolbar);
            ToolbarAnimation.ToolbarAnimate(RelLayout, StorageBox.sharedPreferences.isFirstTimeRun());
            start=false;
        }

        final ImageView setting_btn = view.findViewById(R.id.setting_btn);
        final ImageView archives_btn = view.findViewById(R.id.archives_btn);
        final ImageView home_btn = view.findViewById(R.id.home_btn);
        final ImageView competition_btn = view.findViewById(R.id.competition_btn);
        final ImageView shop_btn = view.findViewById(R.id.shop_btn);

        viewPagerAdapter.addFragment(new FragmentSetting(), "Setting");
        viewPagerAdapter.addFragment(new FragmentArchives(), "Archives");
        viewPagerAdapter.addFragment(new FragmentHome(), "Home");
        viewPagerAdapter.addFragment(new FragmentCompetition(), "Competition");
        viewPagerAdapter.addFragment(new FragmentShop(), "Shop");

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(2);

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0, false);
                ClickAnimation.clickBounce(setting_btn);
            }
        });

        archives_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1, false);
                ClickAnimation.clickBounce(archives_btn);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2, false);
                ClickAnimation.clickBounce(home_btn);
            }
        });

        competition_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, false);
                ClickAnimation.clickBounce(competition_btn);
            }
        });

        shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4, false);
                ClickAnimation.clickBounce(shop_btn);
            }
        });
    }
}
