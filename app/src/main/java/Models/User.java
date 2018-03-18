package Models;


public class User extends Contestant {

    private Number phoneNumber = 0;

    private String email = "empty";

    public User(String username, String grade, String email){

        this.setUsername(username);
        this.setGrade(grade);
        this.setScore(0);
        this.email = email;
    }

    public User(String username, String grade, String email, String city, String schoolName){

        this(username, grade, email);
        this.setCity(city);
        this.setSchoolName(schoolName);
    }

    // Getters

    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters

    public void setPhoneNumber(Number phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
