


package Storage;


import android.content.Context;
import android.content.SharedPreferences;
import com.blackcoin.packdel.bahmanproject.MainActivity;
import Models.Guest;
import Security.AES;
import Security.Cryptography;
import Security.KeyAndIV;
import Utils.log;

public class StorageBox {



    //// TODO: 4/13/18 Move this Constants into the Constants.Java file
    // constants
    private final String SHARED_PREFERENCES_NAME = "QGSP";
    private final String GUEST_FIELD = "Field";
    private final String GUEST_GRADE = "Grade";
    private final String Token = "Token";


    public static boolean ThereIsGuest = false;

    public static boolean ThereIsToken = false;

    private Storage storage;

    public StorageBox(Context ctx) {

        storage = new Storage(ctx,SHARED_PREFERENCES_NAME);

        if(storage.getString(GUEST_FIELD) != null && storage.getString(GUEST_FIELD) != null)
        {
            ThereIsGuest = true;
        }

        if(storage.getString(Token) != null)
        {
            ThereIsToken = true;
        }
    }

    public void save(String key, String value){
        storage.setString(key, value);
    }

    public String load(String key){
        return storage.getString(key);
    }

    public void saveGuest(Guest guest){

        storage.setString(GUEST_FIELD , guest.getField());
        storage.setString(GUEST_GRADE , guest.getGrade());
    }

    public Guest loadGuest(){
        if(ThereIsGuest){
            return new Guest(storage.getString(GUEST_FIELD), storage.getString(GUEST_GRADE));
        }
        return null;
    }

    public String getField(){
        return storage.getString(GUEST_FIELD);
    }

    public String getGrade(){
        return storage.getString(GUEST_GRADE);
    }

    public void saveToken(String token) {
        storage.setString(Token, token);
    }

    public String loadToken(){
        if(ThereIsToken) {
            return storage.getString(Token);
        }else {
            log.print("there is no token saved!");
        }
        return null;
    }
}
