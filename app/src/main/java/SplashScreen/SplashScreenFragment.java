package SplashScreen;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.blackcoin.packdel.bahmanproject.R;
import org.json.JSONObject;
import Dialogs.FieldChoosingDialog;
import Security.RSA;
import Server.Server;
import Server.SocketIO;
import Server.Volley.VolleySingleton;
import Server.Volley.interfaces.OnHttpConnected;
import Storage.StorageBase;
import Storage.StorageBox;
import Toolbar.MenuToolbar;
import Utils.Consts;
import Utils.log;
import Works.LoadShop;
import io.realm.Realm;


interface OnLoadingCompleted{

    public void run();
}

public class SplashScreenFragment extends Fragment {

    private Handler handler;

    private ConstraintLayout splashScreenView;

    public SplashScreenFragment() {

        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        splashScreenView = view.findViewById(R.id.relativeLayout);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setMax(1000);

        OnLoadingCompleted listener = new OnLoadingCompleted(){

            @Override
            public void run() {

                progressBar.incrementProgressBy(200);

                Server.checkConnection(getContext(), new OnHttpConnected() {// 20% http connection
                    @Override
                    public void onConnect() {

                        progressBar.incrementProgressBy(200);

                        onlineJobs(progressBar);
                    }

                    @Override
                    public void onError() {

                        progressBar.incrementProgressBy(200);

                        Toast.makeText(getContext(), "Couldn't reach the server", Toast.LENGTH_SHORT).show();

                        offlineJobs(progressBar);
                    }
                });
            }
        };

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getActivity().runOnUiThread(loading(getContext(), listener));
            }

        }, 100);

        //region splash animations
        //hide the bar
        RelativeLayout layout = getActivity().findViewById(R.id.relativeLayout);
        View toolbar = layout.findViewById(R.id.bottom_toolbar);
        toolbar.setTranslationY(400f);
        //endregion

        return view;
    }

    private void offlineJobs(ProgressBar progressBar) {

        //TODO :: other offline jobs

        SplashScreenSlidAway(progressBar);
    }

    private void onlineJobs(ProgressBar progressBar) {

        new LoadShop().init(progressBar); // 40% percent for shop loading

        //TODO :: other online jobs

        SplashScreenSlidAway(progressBar);
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

    private void SplashScreenSlidAway(ProgressBar progressBar) {

        Runnable showFieldChoosingDialog = () -> {
            // Show the FieldChoosingDialog
            new FieldChoosingDialog(getContext(), getActivity(), getActivity().getSupportFragmentManager()).setup();
        };

        // check if it's not the first time
        if (!Consts.AppFirstTimeRun) {

            //TODO:: ARG -> socket init

            // Setup MenuToolbar
            new MenuToolbar(getActivity().findViewById(R.id.relativeLayout), getActivity().getSupportFragmentManager()).setup();

        } else {

            handler.postDelayed(showFieldChoosingDialog, SplashScreen.delay + 1000);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setProgress(1000);

                splashScreenView.animate().translationX(-4000).setDuration(SplashScreen.latency).start();
            }

        }, SplashScreen.delay);
    }

    private Runnable loading(Context context, OnLoadingCompleted listener){

        return new Runnable() {
            @Override
            public void run() {

                Realm.init(context);

                StorageBox.init();

                StorageBase.init();

                VolleySingleton.init(context);

                if (!RSA.init(context))// if there is no RSA Public Key Available
                {
                    RSA.Is_Public_Key_Available = false;
                    RSA.loadKey();
                    // TODO: 4/13/18  call the server in Loading Activity
                }

                StorageBase.getInstance().createShop();

                listener.run();
            }
        };
    }
}
