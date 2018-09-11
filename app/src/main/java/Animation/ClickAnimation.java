package Animation;


import android.animation.ObjectAnimator;
import android.view.View;

public class ClickAnimation {

    public ClickAnimation() {}

    /*
     * put this method in side of Button ClickListener and give it the view
     */
    public static void clickBounce(View view){
        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f, 0.8f, 0.9f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 0.8f, 0.9f, 1f).setDuration(200).start();
    }
}
