package Utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class Converter {

    public static byte[] Bitmap2ByteArray(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        return stream.toByteArray();
    }

    public static Bitmap byteArray2Bitmap(byte[] bytes){

        return null;
    }
}
