package SplashScreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blackcoin.packdel.bahmanproject.R;

import Dialogs.FieldChoosingDialog;
import Storage.StorageBox;
import Toolbar.MenuToolbar;
import Works.LoadShop;


public class SplashScreenFragment extends Fragment {

    private Handler handler;


    public SplashScreenFragment() {

        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        final ConstraintLayout constraintLayout = view.findViewById(R.id.relativeLayout);

        // load the shop and do the shop jobs
        new LoadShop().init();

        showSplashScreenAnimationAndLoading(constraintLayout);

        //region splash animations
        //hide the bar
        RelativeLayout layout = getActivity().findViewById(R.id.relativeLayout);
        View toolbar = layout.findViewById(R.id.bottom_toolbar);
        toolbar.setTranslationY(400f);

        Runnable showFieldChoosingDialog = () -> {
            // Show the FieldChoosingDialog
            new FieldChoosingDialog(getContext(), getActivity(), getActivity().getSupportFragmentManager()).setup();
        };

        // check if it's not the first time
        if (!StorageBox.sharedPreferences.isFirstTimeRun()) {

            // Setup MenuToolbar
            new MenuToolbar(getActivity().findViewById(R.id.relativeLayout), getActivity().getSupportFragmentManager()).setup();

        } else {

            handler.postDelayed(showFieldChoosingDialog, SplashScreen.delay + 1000);
        }

        return view;
        //endregion
    }


    private void showSplashScreenAnimationAndLoading(final ConstraintLayout constraintLayout) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout.animate().translationX(-4000).setDuration(SplashScreen.latency).start();
            }

        }, SplashScreen.delay);
    }

}
