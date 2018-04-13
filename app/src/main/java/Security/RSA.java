package Security;

import android.content.Context;
import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Storage.Storage;
import Utils.C;

import static javax.crypto.Cipher.ENCRYPT_MODE;


public class RSA {

    private static String PUBLIC_KEY; // a base64 string containing RSA Public Key
    // we check this bool var to weather call to server to get Public Key or not (in Loading Activity)
    public static boolean Is_Public_Key_Available = false;

    private static final String CIPHER_RSA_WITH_PADDING = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
    private static final int BASE64_FLAG = Base64.DEFAULT;

    public static boolean init(Context ctx)
    {
        Storage storage = new Storage(ctx,C.StorageSH.PREFS_NAME_RSA);
        PUBLIC_KEY = storage.getString(C.StorageSH.KEY_RSA_PUBLIC_KEY);
        return PUBLIC_KEY != null;
    }

    public static byte[] encrypt(byte[] data)
    {
        if (PUBLIC_KEY == null || PUBLIC_KEY.equals(""))
        {
            throw new NullPointerException("PUBLIC_KEY is empty, load from storage or get it from server");
        }
        else
        {
            try
            {
                X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.decode(PUBLIC_KEY, Base64.DEFAULT));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(publicSpec);

                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return cipher.doFinal(data);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return null;
    }


}
