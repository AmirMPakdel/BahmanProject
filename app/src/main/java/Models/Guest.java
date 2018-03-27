package Models;


public class Guest {

    private String field;

    private String grade;

    public Guest(String field, String grade) {
        this.field = field;
        this.grade = grade;
    }

    public String getField() { return field; }

    public String getGrade() {
        return grade;
    }

    public void setField(String major) { this.field = major; }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
