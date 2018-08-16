package SplashScreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import Dialogs.FieldChoosingDialog;
import Storage.StorageBox;
import Toolbar.MenuToolbar;


public class SplashScreenFragment extends Fragment {


    public SplashScreenFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        final RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);

        showSplashScreenAnimation(relativeLayout);

        // hide the toolbar
        RelativeLayout layout = getActivity().findViewById(R.id.relativeLayout);
        View toolbar = layout.findViewById(R.id.bottom_toolbar);
        toolbar.setTranslationY(400f);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {

                // Show the FieldChoosingDialog
                new FieldChoosingDialog(getContext(), getActivity(), getActivity().getSupportFragmentManager()).setup();
            }
        };



        // check if it's not the first time
        if(!StorageBox.sharedPreferences.isFirstTimeRun()){

            // Setup MenuToolbar
            new MenuToolbar(getActivity().findViewById(R.id.relativeLayout), getActivity().getSupportFragmentManager()).setup();

        }else {

            Handler handler = new Handler();
            handler.postDelayed(runnable1, SplashScreen.delay + 1000);
        }

        return view;
    }


    private void showSplashScreenAnimation(final RelativeLayout relativeLayout){

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                relativeLayout.animate().translationX(-4000).setDuration(SplashScreen.latency).start();

            }
        };

        handler.postDelayed(runnable, SplashScreen.delay);
    }

}
