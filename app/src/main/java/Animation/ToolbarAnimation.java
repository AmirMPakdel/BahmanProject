package Animation;


import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

import SplashScreen.SplashScreen;


public class ToolbarAnimation {

    public static void ToolbarAnimate(final View toolbar, boolean firstTimeRun){

        if(!firstTimeRun) {
            ObjectAnimator.ofFloat(toolbar, View.TRANSLATION_Y, 400f).setDuration(1000).start();

            Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator.ofFloat(toolbar, View.TRANSLATION_Y, 0f).setDuration(1500).start();
                }
            };

            handler.postDelayed(runnable, SplashScreen.delay);
        }
    }

    public static void TabLayoutAnimate(final View tablayout, boolean firstTimeRun){

        if(!firstTimeRun) {
            ObjectAnimator.ofFloat(tablayout, View.TRANSLATION_Y, -400f).setDuration(1000).start();

            Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator.ofFloat(tablayout, View.TRANSLATION_Y, 0f).setDuration(1500).start();
                }
            };

            handler.postDelayed(runnable, SplashScreen.delay);
        }
    }

}