package Menu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackcoin.packdel.bahmanproject.R;
import Archives.*;


public class FragmentArchives extends Fragment {


    public FragmentArchives() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_archives, container, false);

        Archives archives = new Archives();

        ViewPager archives_viewPager = (ViewPager) view.findViewById(R.id.archives_viewPager);

        ArchivesViewPagerAdapter archivesViewPagerAdapter = new ArchivesViewPagerAdapter(getActivity().getSupportFragmentManager());


        archivesViewPagerAdapter.addFragment(new FragmentTestArchives(), "Tests");
        archivesViewPagerAdapter.addFragment(new FragmentVideoArchives(), "Videos");
        archivesViewPagerAdapter.addFragment(new FragmentBrochureArchives(), "Brochure");

        archives_viewPager.setAdapter(archivesViewPagerAdapter);

        return view;
    }

}
