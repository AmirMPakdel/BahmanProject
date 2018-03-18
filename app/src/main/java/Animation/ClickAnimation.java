package Animation;


import android.animation.ObjectAnimator;
import android.view.View;

public class ClickAnimation {

    public ClickAnimation() {}

    public static void clickBounce(View view){
        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.8f, 1f).setDuration(400).start();
        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.8f, 1f).setDuration(400).start();
    }
}
