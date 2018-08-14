package Storage;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.util.ArrayList;
import java.util.List;

import Models.Book;
import Models.Chest;
import Utils.Consts;

public class StorageLite1 {

    private SQLiteDatabase db;

    public StorageLite1(Context context){

        db = context.openOrCreateDatabase("QG", Context.MODE_PRIVATE, null);

        // check if it's the first time
        if(!StorageBox1.ThereIsGuest){
            db.execSQL("CREATE TABLE IF NOT EXISTS chests(field VARCHAR, capacity INT(4), load INT(4))");
        }

    }

    public void fillChestsTable(){

        List<String> bookList = new Book().getBooksList(MainActivity.storageBox1.getField());
        for(int i=0; i<bookList.size(); i++){
            db.execSQL("INSERT INTO chests(field, capacity, load) VALUES('"+bookList.get(i)+"', "+ Consts.CHEST_CAPACITY+", 0)");
        }
    }

    public List<Chest> getChestsList(){

        List<Chest> chests = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM chests", null);

        int fieldIndex = c.getColumnIndex("field");
        int capacityIndex = c.getColumnIndex("capacity");
        int loadIndex = c.getColumnIndex("load");

        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++){

            String field = c.getString(fieldIndex);
            int capacity = c.getInt(capacityIndex);
            int load = c.getInt(loadIndex);

            chests.add(new Chest(field, capacity, load));

            c.moveToNext();
        }

        c.close();

        return chests;
    }
}
