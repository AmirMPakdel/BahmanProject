package Models;


public class Chest {

    private String field;

    private int capacity;

    private int load;

    public Chest(String field, int capacity, int load) {
        this.field = field;
        this.capacity = capacity;
        this.load = load;
    }

    ///////////

    public String getField() {
        return field;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLoad() {
        return load;
    }

    /////////////

    public void setField(String field) {
        this.field = field;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
