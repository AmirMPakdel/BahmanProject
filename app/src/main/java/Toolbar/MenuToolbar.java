package Toolbar;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blackcoin.packdel.bahmanproject.R;

import Animation.ClickAnimation;
import Menu.FragmentArchives;
import Menu.FragmentCompetition;
import Menu.FragmentHome;
import Menu.FragmentSetting;
import Menu.FragmentShop;
import Menu.ViewPagerAdapter;

public class MenuToolbar {

    private View view;

    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    public MenuToolbar(View view, FragmentManager SupportFragmentManager) {

        this.view = view;
        viewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(SupportFragmentManager);
    }


    public void setup(){

        final ImageView setting_btn = (ImageView) view.findViewById(R.id.setting_btn);
        final ImageView archives_btn = (ImageView) view.findViewById(R.id.archives_btn);
        final ImageView home_btn = (ImageView) view.findViewById(R.id.home_btn);
        final ImageView competition_btn = (ImageView) view.findViewById(R.id.competition_btn);
        final ImageView shop_btn = (ImageView) view.findViewById(R.id.shop_btn);

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
                viewPager.setCurrentItem(0, true);
                ClickAnimation.clickBounce(setting_btn);
            }
        });

        archives_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1, true);
                ClickAnimation.clickBounce(archives_btn);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2, true);
                ClickAnimation.clickBounce(home_btn);
            }
        });

        competition_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, true);
                ClickAnimation.clickBounce(competition_btn);
            }
        });

        shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4, true);
                ClickAnimation.clickBounce(shop_btn);
            }
        });

    }
}
