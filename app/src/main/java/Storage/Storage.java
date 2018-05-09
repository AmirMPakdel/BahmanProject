package Storage;


import android.content.Context;
import android.content.SharedPreferences;

import Security.Cryptography;
import Utils.log;

/**
 * this class is a wrapper around Android Shared Preferences
 */

public class Storage // QG Storage System
{
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor = null;
    private Cryptography cryptography;


    /**
     *
     * @param ctx
     * @param storageName is a const string from MConstants.Storage
     */
    public Storage(Context ctx , String storageName)
    {
        cryptography= new Cryptography();
        if (ctx == null) throw new NullPointerException("Context Must not be null");
        prefs = ctx.getSharedPreferences(storageName, Context.MODE_PRIVATE);
    }


    public void setString(String key, String value) {

        String encData = cryptography.encrypt(value);

        editor = prefs.edit();
        editor.putString(key, encData);
        editor.apply();
    }

    /*public void setFloat(String key, float value) {
        editor = prefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }
    public void setInt(String key, int value) {
        editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }*/


    /**
     * get a String from Storage
     * @param key
     * @return default value is null (if value doesn't exist)
     */
    public String getString(String key) {

        String encData = prefs.getString(key, null);

        return cryptography.decrypt(encData);
    }



    /**
     * @return default value is -1.0f (if value doesn't exist)
     */


    /*public float getFloat(String key) {
        return prefs.getFloat(key, -1.0f);
    }*/

    /**
     * @return default value is -1 (if value doesn't exist)
     */
    /*public int getInt(String key) {
        return prefs.getInt(key, -1);
    }*/


    public boolean remove(String key) {
        try {
            editor = prefs.edit();
            editor.remove(key);
            editor.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
