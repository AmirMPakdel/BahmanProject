package Archives;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blackcoin.packdel.bahmanproject.R;

import Animation.ClickAnimation;

public class Archives {

    private View view;

    private final ViewPager archives_viewPager;

    public Archives(View view, FragmentManager SupportFragmentManager) {

        this.view = view;

        archives_viewPager = (ViewPager) this.view.findViewById(R.id.archives_viewPager);

        ArchivesViewPagerAdapter archivesViewPagerAdapter = new ArchivesViewPagerAdapter(SupportFragmentManager);


        archivesViewPagerAdapter.addFragment(new FragmentVideoArchives(), "Videos");
        archivesViewPagerAdapter.addFragment(new FragmentTestArchives(), "Tests");
        archivesViewPagerAdapter.addFragment(new FragmentBrochureArchives(), "Brochure");

        archives_viewPager.setAdapter(archivesViewPagerAdapter);

        archives_viewPager.setCurrentItem(1);

    }

    public void setup(){

        final ImageView videos_list_btn = (ImageView) view.findViewById(R.id.videos_list_btn);
        final ImageView tests_list_btn = (ImageView) view.findViewById(R.id.tests_list_btn);
        final ImageView brochures_list_btn = (ImageView) view.findViewById(R.id.brochures_list_btn);

        videos_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                archives_viewPager.setCurrentItem(0, true);
                ClickAnimation.clickBounce(videos_list_btn);
            }
        });

        tests_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                archives_viewPager.setCurrentItem(1, true);
                ClickAnimation.clickBounce(tests_list_btn);
            }
        });

        brochures_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                archives_viewPager.setCurrentItem(2, true);
                ClickAnimation.clickBounce(brochures_list_btn);
            }
        });
    }
}
