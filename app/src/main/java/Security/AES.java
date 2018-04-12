package Security;

import android.util.Base64;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AES {

    String publicKey;

    public SecretKey AES_Key;


    public AES(String publicKey){

        this.publicKey = publicKey;

        this.AES_Key = generate_AES_Key();

    }

    private SecretKey generate_AES_Key(){

        SecretKey key = null;


        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            key = keyGenerator.generateKey();

            return key;


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        MainActivity.log("Error -> SecretKey is null");
        return key;
    }

    private String Encrypted_AES_Key(PublicKey publicKey, byte[] byteData) {

        String AES_Key_Encrypted = null;

        Cipher cipher = null;

        try {

            cipher = Cipher.getInstance("RSA/ECB/NoPadding");

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedByteData = cipher.doFinal(byteData);

            AES_Key_Encrypted = Base64.encodeToString(encryptedByteData, Base64.NO_WRAP);

            return AES_Key_Encrypted;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        MainActivity.log("ERROR -> AES_Key_Encrypted is null!");

        return AES_Key_Encrypted;
    }


    public String getRandomKey(int size){
        Character[] Eng_word_char_array = new Character[]{'0','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','z'};
        String s ="";
        for(int i=0; i<size; i++){
            int randomNum = new Random().nextInt(Eng_word_char_array.length-1);
            s += Eng_word_char_array[randomNum];
        }
        return s;
    }
}
