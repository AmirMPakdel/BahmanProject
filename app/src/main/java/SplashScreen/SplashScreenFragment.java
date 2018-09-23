package SplashScreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import org.json.JSONObject;

import Dialogs.FieldChoosingDialog;
import Server.SocketIO;
import Storage.StorageBox;
import Toolbar.MenuToolbar;
import Utils.Consts;
import Utils.log;


public class SplashScreenFragment extends Fragment {


    public SplashScreenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        final RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);

        showSplashScreenAnimation(relativeLayout);

        // connect to server and fetch info
        initSocket();


        //region splash animations
        //hide the bar
        RelativeLayout layout = getActivity().findViewById(R.id.relativeLayout);
        View toolbar = layout.findViewById(R.id.bottom_toolbar);
        toolbar.setTranslationY(400f);

        Runnable runnable1 = () -> {
            // Show the FieldChoosingDialog
            new FieldChoosingDialog(getContext(), getActivity(), getActivity().getSupportFragmentManager()).setup();
        };


        // check if it's not the first time
        if (!StorageBox.sharedPreferences.isFirstTimeRun()) {

            // Setup MenuToolbar
            new MenuToolbar(getActivity().findViewById(R.id.relativeLayout), getActivity().getSupportFragmentManager()).setup();

        } else {

            Handler handler = new Handler();
            handler.postDelayed(runnable1, SplashScreen.delay + 1000);
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
            Log.d(Consts.DEBUG_TAG, "initSocket: " + err.getMessage());
        }
    }


    private void showSplashScreenAnimation(final RelativeLayout relativeLayout) {
        new Handler().postDelayed(() -> relativeLayout.animate().translationX(-4000).setDuration(SplashScreen.latency).start(), SplashScreen.delay);
    }

}
