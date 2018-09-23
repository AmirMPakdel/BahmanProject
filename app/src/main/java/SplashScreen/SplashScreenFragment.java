package SplashScreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.R;

import org.json.JSONObject;

import Dialogs.FieldChoosingDialog;
import Server.SocketIO;
import Storage.StorageBox;
import Toolbar.MenuToolbar;
import Utils.Consts;
import Utils.log;
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


    private void initSocket() {
        try {
            SocketIO tempSoc = SocketIO.getInstance();
            SocketIO.LOG = false;

            tempSoc.on(Consts.socketEvents.ONCONNECT, obj -> log.print(obj.toString()));
            tempSoc.on(Consts.socketEvents.UPDATE_USER_INFO_splashScrren, obj -> log.print("CALLBACK Splash => " + obj.toString()));

            tempSoc.connect();

            JSONObject userInfo = new JSONObject();
            userInfo.put("appVersion", 22);

            tempSoc.send(Consts.socketEvents.UPDATE_USER_INFO_splashScrren, userInfo);


        } catch (Exception err) {
            Toast.makeText(getContext(), "initSocket: " + err.getMessage(), Toast.LENGTH_SHORT);
            Log.d(Consts.DEBUG_TAG, "initSocket: " + err.getMessage());
        }
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
