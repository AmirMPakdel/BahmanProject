package Security;


import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Utils.Consts;
import Utils.log;

public class Cryptography
{
    private SecretKeySpec secretKeySpec;


    public Cryptography(){

        try {
            secretKeySpec = generateKey(Consts.StorageBoxKEY);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String encrypt(String Data){

        String enVal = null;


        try {
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] enValBytes = cipher.doFinal(Data.getBytes());

            enVal = Base64.encodeToString(enValBytes, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return enVal;
    }


    public String decrypt(String encryptedData){

        String decryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decodedVal = Base64.decode(encryptedData, Base64.DEFAULT);

            byte[] decryptedVal = cipher.doFinal(decodedVal);

            decryptedData = new String(decryptedVal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedData;
    }


    private SecretKeySpec generateKey(String storageBoxKEY) throws Exception {

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] bytes = storageBoxKEY.getBytes("UTF-8");

        digest.update(bytes, 0, bytes.length);

        byte[] key = digest.digest();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        return secretKeySpec;
    }

}
