package SplashScreen;


import android.support.v4.app.FragmentManager;
import com.blackcoin.packdel.bahmanproject.R;

public class SplashScreen {

    static final int delay = 2000;
    static final int latency = 3000;

    private FragmentManager SupportFragmentManager;


    public SplashScreen(FragmentManager SupportFragmentManager){

        this.SupportFragmentManager = SupportFragmentManager;
    }

    public void show(){

        SupportFragmentManager.beginTransaction().add(R.id.splashScreen_frame, new SplashScreenFragment()).commit();
    }
}
