package Animation;

import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.blackcoin.packdel.bahmanproject.R;


public class ShopToolbarAnimation {

    public static void animate(View view, Resources resources){


        float distance = resources.getDimensionPixelSize(R.dimen.toolbar_anim);
        float objects_dist = resources.getDimensionPixelSize(R.dimen.toolbar_anim_objects);

        view.setTranslationY(-distance);

        view.findViewById(R.id.shop_pic).setTranslationY(-objects_dist);
        view.findViewById(R.id.shop_title).setTranslationY(-objects_dist);
        view.findViewById(R.id.toolbar_title).setTranslationY(-objects_dist);


        Handler handler = new Handler();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {

                view.animate().translationY(0).setDuration(500).setInterpolator(new AccelerateInterpolator()).start();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {

                view.findViewById(R.id.shop_pic).animate().translationY(0).setDuration(500).setInterpolator(new BounceInterpolator()).start();
                view.findViewById(R.id.shop_title).animate().translationY(0).setDuration(800).setInterpolator(new BounceInterpolator()).start();
                view.findViewById(R.id.toolbar_title).animate().translationY(0).setDuration(1100).setInterpolator(new BounceInterpolator()).start();
            }
        };

        handler.postDelayed(runnable1, 400);
        handler.postDelayed(runnable2, 900);

    }
}
