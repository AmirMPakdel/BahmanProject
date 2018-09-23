package Security;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES
{
    public static byte[] encryptToBytes(KeyAndIV keyAndIV, byte[] data)
    {
        Cipher cipher;

        try
        {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyAndIV.getKey(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(keyAndIV.getIv());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);

            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    public static String encryptToBase64(KeyAndIV keyAndIV, byte[] data)
    {
        try
        {
            return Base64.encodeToString(encryptToBytes(keyAndIV, data), Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] decryptFromBytes(KeyAndIV keyAndIV, byte[] data)
    {
        Cipher cipher;

        try
        {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyAndIV.getKey(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(keyAndIV.getIv());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParameterSpec);

            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    public static byte[] decryptFromBase64String(KeyAndIV keyAndIV, String base64String)
    {
        try
        {
            return decryptFromBytes(keyAndIV,Base64.decode(base64String.getBytes("utf-8"),Base64.DEFAULT));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
