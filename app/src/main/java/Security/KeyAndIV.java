package Security;


import android.util.Base64;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import Utils.log;

/**
 * this class generates and holds the secretKey and IV for AES-128 CBC with PKCS5 Padding method
 */
public class KeyAndIV
{
    private SecretKey secretKey;
    private byte[] iv;

    public KeyAndIV()
    {
        init();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    /**
     * initialize the secret key and IV
     */
    private void init()
    {
        try
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();

            SecureRandom secureRandom = new SecureRandom();
            iv = new byte[16];
            secureRandom.nextBytes(iv);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            log.print(e.toString());
        }
    }

    /**
     * this method attach key(16B) and IV(16B) and create a 32 Bytes array
     * then encrypt it with RSA public Key
     * and in the end, convert it to base64
     * @return a base64 String containing encrypted Secret Key and IV
     */
    public String combineAndEncrypt()
    {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put(getKey());
        buffer.put(getIv());
        return Base64.encodeToString(RSA.encrypt(buffer.array()),Base64.DEFAULT);
    }

    public void destroy()
    {
        if(! secretKey.isDestroyed())
        {
            try {
                secretKey.destroy();
            } catch (DestroyFailedException e) {
                e.printStackTrace();
            }
        }

        Arrays.fill(iv,(byte)0);
        iv = null;
    }

    public byte[] getIv() {
        return iv;
    }
    public byte[] getKey()
    {
        return secretKey.getEncoded();
    }
}

