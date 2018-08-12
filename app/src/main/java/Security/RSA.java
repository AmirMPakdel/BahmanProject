package Security;

import android.content.Context;
import android.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import Storage.Storage1;
import Utils.C;


public class RSA {

    private static String PRIVATE_KEY;
    private static String PUBLIC_KEY; // a base64 string containing RSA Public Key
    // we check this bool var to weather call to server to get Public Key or not (in Loading Activity)
    public static boolean Is_Public_Key_Available = false;

    private static final String CIPHER_RSA_WITH_PADDING = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
    private static final int BASE64_FLAG = Base64.DEFAULT;

    public static boolean init(Context ctx)
    {
        Storage1 storage1 = new Storage1(ctx,C.StorageSH.PREFS_NAME_RSA);
        PUBLIC_KEY = storage1.getString(C.StorageSH.KEY_RSA_PUBLIC_KEY);
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

                Cipher cipher = Cipher.getInstance("RSA");
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


    public static byte[] decrypt(byte[] data)
    {
        if (PUBLIC_KEY == null || PUBLIC_KEY.equals(""))
        {
            throw new NullPointerException("PRIVATE_KEY is empty, load from storage or get it from server");
        }
        else
        {
            try
            {
                PKCS8EncodedKeySpec publicSpec = new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_KEY, Base64.DEFAULT));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = keyFactory.generatePrivate(publicSpec);


                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return cipher.doFinal(data);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static boolean loadKey()
    {
        PUBLIC_KEY =
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqFqJTm6o0sRDCjs9wRAbBJcO6\n" +
                "NTPWRojDplBYHfSdFioqt5HllpjPW5dslC07ru+/NVVSs01yh0PMLp4PUbhJl62B\n" +
                "Zn+HU/ytW2y5TZfHUXisy5Qh6jSxGZ7fF05VTMwbTu4NYotHw7wIDzsl7IvzINfb\n" +
                "gsvSQsY9Gh52D4n1UwIDAQAB\n";

        PRIVATE_KEY = "MIICWwIBAAKBgQCqFqJTm6o0sRDCjs9wRAbBJcO6NTPWRojDplBYHfSdFioqt5Hl\n" +
                "lpjPW5dslC07ru+/NVVSs01yh0PMLp4PUbhJl62BZn+HU/ytW2y5TZfHUXisy5Qh\n" +
                "6jSxGZ7fF05VTMwbTu4NYotHw7wIDzsl7IvzINfbgsvSQsY9Gh52D4n1UwIDAQAB\n" +
                "AoGACW8V89M3fpKvnkrzgIOVaFMi5woaZPEmlst7u1d2ANyA3DuwUO6obR/kZzZU\n" +
                "x/GtBIr9v4eIbSjJfuK7C5h5wywLpjnPO9ABHIowxTL7J+XB28ZA9En8fNgPhu7b\n" +
                "ljxM0WlP9OZ3EwCLTH6uAzArg7amHbER79oAD8Qjx7OWefECQQDQJSjLWbbeRq0x\n" +
                "XFPYXxGeJ7ZwNApInbTI8gslWm1fioCMfTGO58n4N+SwIr5s3JaEV2GOUrytfGUF\n" +
                "4vzvAAm7AkEA0TGPybzVy6LyevZPpKn2x9PY+zEmbMkkI0tbszyUVq5K9Q0wuzPV\n" +
                "Jo5q7RwTZZPnVwDE8izRajGQOtuiTPcdSQJATleeKiPDU5gweeKxYEAwJmH2JfdA\n" +
                "Y6KQOA36Kf4GKq67waUhcRNHDpkjBzScjebc9ETbYDE9+OJYN8X+w4o1HwJAPeif\n" +
                "+AEFeH1hq1gnm8CdAuhEB5q1F26zXiqYGcQs1jZAgqMDr/MX+pAdPsyXRPFvfkUB\n" +
                "A/aVhwrh9zk2n/Zl2QJAMG6LYJxw092DxM2wyjyPFRMK8Ug8W2eFXmwG0odrBUwE\n" +
                "t3EswyEzGrSbBz+ID5x5tZT/O2PO5sTFrfWFwfx3cg==";

        Is_Public_Key_Available = true;
        return true;
    }


}
