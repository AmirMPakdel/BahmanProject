package RealmObjects;

import io.realm.RealmObject;

public class SharedPreferences extends RealmObject{

    private boolean firstTimeRun;

    private boolean isGuest;

    private String id;

    private String token;

    private String field;

    private String grade;

    private String username;

    private String password;

    private String city;

    private String schoolName;

    private double score;

    private double phoneNumber;

    private String email;

    private String KEY_RSA_PUBLIC_KEY;

    public SharedPreferences(){
    }

    public SharedPreferences(boolean firstTimeRun){

        this.firstTimeRun = firstTimeRun;
    }

    public boolean isFirstTimeRun() {
        return firstTimeRun;
    }

    public void setFirstTimeRun(boolean firstTimeRun) {
        this.firstTimeRun = firstTimeRun;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKEY_RSA_PUBLIC_KEY() {
        return KEY_RSA_PUBLIC_KEY;
    }

    public void setKEY_RSA_PUBLIC_KEY(String KEY_RSA_PUBLIC_KEY) {
        this.KEY_RSA_PUBLIC_KEY = KEY_RSA_PUBLIC_KEY;
    }
}
