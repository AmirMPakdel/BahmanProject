package Security;

import android.util.Base64;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {

    private static SecretKey Last_AES_Key;

    public static String decrypt(String EncryptedData) {

        try {

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, Last_AES_Key);

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
            SecretKey secretKey = generateKey("AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(stringData.getBytes());

            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        MainActivity.log("ERROR -> encrypt String is null");

        return new String();
    }

    private static SecretKey generateKey(String aes) throws Exception{

        KeyGenerator keyGenerator = KeyGenerator.getInstance(aes);

        SecretKey secretKey = keyGenerator.generateKey();

        Last_AES_Key = secretKey;

        return secretKey;
    }
}
