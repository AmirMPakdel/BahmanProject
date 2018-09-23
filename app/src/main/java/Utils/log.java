package Utils;

import android.util.Log;

public class log {


    public static void print(String text) {
        Log.d(Consts.DEBUG_TAG, text);
    }

    public static void test(String text) {
        Log.d(Consts.TEST_TAG, text);
    }
}
