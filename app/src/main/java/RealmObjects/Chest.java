package RealmObjects;

import Utils.Consts;
import io.realm.RealmObject;

public class Chest extends RealmObject{

    private String book;

    private int capacity;

    private int load;

    public Chest() {

        this.capacity = Consts.NEW_CHEST_CAPACITY;
        this.load = 0;
    }

    public Chest(String field) {

        this.book = field;
        this.capacity = Consts.NEW_CHEST_CAPACITY;
        this.load = 0;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
