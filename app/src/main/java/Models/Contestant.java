package Models;


class Contestant {

    private String username = "empty";

    private String grade = "empty";

    private String city = "empty";

    private String schoolName = "empty";

    private long score = 0;

    // Getters

    public String getUsername() {
        return username;
    }

    public String getGrade() {
        return grade;
    }

    public String getCity() {
        return city;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public long getScore() {
        return score;
    }

    // Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
