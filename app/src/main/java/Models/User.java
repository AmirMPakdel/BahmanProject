package Models;


public class User extends Contestant {

    private Number phoneNumber = 0;

    private String email = "empty";

    public User(String major, String grade, String username, String city, String schoolName, long score, Number phoneNumber, String email) {
        super(major, grade, username, city, schoolName, score);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    public void setPhoneNumber(Number phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
