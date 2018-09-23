package Models;


public class Chest {

    private String book;

    private int capacity;

    private int load;

    public Chest(String field, int capacity, int load) {
        this.book = field;
        this.capacity = capacity;
        this.load = load;
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
