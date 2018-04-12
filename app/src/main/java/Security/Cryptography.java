package Security;

import android.util.Base64;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {

    public static String decrypt(String EncryptedData) {

        try {
            SecretKeySpec secretKeySpec = generateKey("AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decryptedBytes = cipher.doFinal(Base64.decode(EncryptedData, Base64.DEFAULT));

            return new String(decryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        MainActivity.log("ERROR -> decrypt String is null");

        return new String();
    }

    public static String encrypt(String stringData){

        try {
            SecretKeySpec secretKeySpec = generateKey("AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(stringData.getBytes());

            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        MainActivity.log("ERROR -> encrypt String is null");

        return new String();
    }

    private static SecretKeySpec generateKey(String aes) throws Exception{

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] bytes = aes.getBytes("UTF-8");

        digest.update(bytes, 0, bytes.length);

        byte[] key = digest.digest();

        MainActivity.log("byte[] key :"+key);

        return new SecretKeySpec(key, "AES");
    }
}
