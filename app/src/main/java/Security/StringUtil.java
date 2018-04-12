package Security;

import android.util.Base64;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringUtil {

    public static String xorTheString(String s){

        String NewString="";

        int par2 = 15;

        for(int i=0; i<s.length(); i++){

            NewString += (char)(s.charAt(i) ^ par2);
        }

        return NewString;
    }

    public static String stringToBase64(String s){

        byte[] str = s.getBytes();

        String str64 = Base64.encodeToString(str, Base64.DEFAULT);

        return str64;

    }

    public static String Base64ToString(String s){

        return new String(Base64.decode(s, Base64.DEFAULT));
    }

}
