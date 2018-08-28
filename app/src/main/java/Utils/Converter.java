package Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Converter {

    public static byte[] Bitmap2ByteArray(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    public static Bitmap byteArray2Bitmap(byte[] bytes){

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static byte[] InputStream2byteArray(InputStream inputStream){

        try {

            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

            // this is storage overwritten on each iteration with bytes
            int bufferSize = 1024;
            byte[] bytes = new byte[bufferSize];

            // we need to know how may bytes were read to write them to the byteBuffer
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                byteBuffer.write(bytes, 0, len);
            }

            // and then we can return your byte array.
            return  byteBuffer.toByteArray();

        }catch (Exception e){

        }

        return null;
    }

}
