package Models;


class Contestant extends Guest{

    private String username = "empty";

    private String city = "empty";

    private String schoolName = "empty";

    private long score = 0;

    public Contestant(String major, String grade, String username, String city, String schoolName, long score) {
        super(major, grade);
        this.username = username;
        this.city = city;
        this.schoolName = schoolName;
        this.score = score;
    }

    public String getUsername() {
        return username;
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


    public void setUsername(String username) {
        this.username = username;
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
