package Utils;

import android.util.Base64;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringUtil {

    public static String xorTheString(String s){

        StringBuilder stringBuilder = new StringBuilder();

        int par2 = 15;

        for(int i=0; i<s.length(); i++){

            stringBuilder.append((char)(s.charAt(i) ^ par2));
        }

        return stringBuilder.toString();
    }

    public static String stringToBase64(String s){

        try
        {
            return Base64.encodeToString(s.getBytes("utf-8"), Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String base64ToString(String s){

        try
        {
        return new String(Base64.decode(s, Base64.DEFAULT), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
