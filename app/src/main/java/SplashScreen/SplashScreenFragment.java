package SplashScreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blackcoin.packdel.bahmanproject.R;


public class SplashScreenFragment extends Fragment {


    public SplashScreenFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        final RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                relativeLayout.animate().translationX(-4000).setDuration(SplashScreen.latency).start();
            }
        };

        handler.postDelayed(runnable, SplashScreen.delay);


        return view;
    }

}
