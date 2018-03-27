package Storage;


import android.content.SharedPreferences;
import com.blackcoin.packdel.bahmanproject.MainActivity;
import Models.Guest;

public class StorageBox {

    public static String SHARED_PREFERENCES_NAME = "QGSP";
    private String GUEST_FIELD = "Field";
    private String GUEST_GRADE = "Grade";

    public static boolean ThereIsGuest = false;

    private SharedPreferences save;
    private SharedPreferences.Editor editor;


    public StorageBox(SharedPreferences sharedPreferences){

        save = sharedPreferences;
        editor = save.edit();


        String Field = save.getString(GUEST_FIELD,null);
        String Grade = save.getString(GUEST_GRADE, null);

        MainActivity.log("storage constructor : grade : "+Grade);

        if(Field != null && Grade != null){

            ThereIsGuest = true;
        }
    }

    public void saveGuest(Guest guest){

        editor.putString(GUEST_FIELD, guest.getField()).apply();
        editor.putString(GUEST_GRADE, guest.getGrade()).apply();

        MainActivity.log("saved ! -> "+guest.getGrade());

    }

    public Guest loadGuest(){

        String Field = save.getString(GUEST_FIELD,null);
        String Grade = save.getString(GUEST_GRADE, null);

        if(ThereIsGuest){
            return new Guest(Field, Grade);
        }
        return null;
    }

    public String getField(){
        return save.getString(GUEST_FIELD, null);
    }

    public String getGrade(){
        return save.getString(GUEST_GRADE, null);
    }

}
