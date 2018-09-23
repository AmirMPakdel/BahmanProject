package Server.Volley.interfaces;

import android.graphics.Bitmap;

public interface OnBitmapReceived {

    void onResponse(Bitmap response);
    void onError(String error);

}
