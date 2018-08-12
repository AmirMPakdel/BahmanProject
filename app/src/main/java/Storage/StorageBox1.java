


package Storage;


import android.content.Context;

import Models.Guest;
import Utils.log;

public class StorageBox1 {



    //// TODO: 4/13/18 Move this Constants into the Constants.Java file
    // constants
    private final String SHARED_PREFERENCES_NAME = "QGSP";
    private final String GUEST_FIELD = "Field";
    private final String GUEST_GRADE = "Grade";
    private final String Token = "Token";


    public static boolean ThereIsGuest = false;

    private static boolean ThereIsToken = false;

    private Storage1 storage1;

    public StorageBox1(Context ctx) {

        storage1 = new Storage1(ctx,SHARED_PREFERENCES_NAME);

        if(storage1.getString(GUEST_FIELD) != null && storage1.getString(GUEST_FIELD) != null)
        {
            ThereIsGuest = true;
        }

        if(storage1.getString(Token) != null)
        {
            ThereIsToken = true;
        }
    }

    public void save(String key, String value){
        storage1.setString(key, value);
    }

    public String load(String key){
        return storage1.getString(key);
    }

    public void saveGuest(Guest guest){

        storage1.setString(GUEST_FIELD , guest.getField());
        storage1.setString(GUEST_GRADE , guest.getGrade());
    }

    public Guest loadGuest(){
        if(ThereIsGuest){
            return new Guest(storage1.getString(GUEST_FIELD), storage1.getString(GUEST_GRADE));
        }
        return null;
    }

    public String getField(){
        return storage1.getString(GUEST_FIELD);
    }

    public String getGrade(){
        return storage1.getString(GUEST_GRADE);
    }

    public void saveToken(String token) {
        storage1.setString(Token, token);
    }

    public String loadToken(){
        if(ThereIsToken) {
            return storage1.getString(Token);
        }else {
            log.print("there is no token saved!");
        }
        return null;
    }
}
