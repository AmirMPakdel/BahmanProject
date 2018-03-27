package Models;


import java.util.List;

public class Test {

    private String id;

    private String name; // user can set name for a test

    private String field; // math or physics or eng or ...

    private int fieldColor;

    private int fieldString;

    private int year; // if it's Konkurs then which year?

    private String major; // mathematics major or biology major or arts major or ...

    private int grade; // 1st grade or 2nd grade ... 4th grade

    private int difficulty; // from 1 to 5, 5 is the hardest

    private String question; // question body

    private List<String> possibleAnswers; // there can be 4 possible answer

    private String CorrectAnswer;

    private String CompleteAnswer; // answer with information and formula

    public Test(String id, String name, String field, int year, String major, int grade, int difficulty, String question, List<String> possibleAnswers, String correctAnswer, String completeAnswer) {
        this.id = id;
        this.name = name;
        this.field = field;
        this.year = year;
        this.major = major;
        this.grade = grade;
        this.difficulty = difficulty;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        CorrectAnswer = correctAnswer;
        CompleteAnswer = completeAnswer;
        fieldColor = Book.setFieldColor(field);
        fieldString = Book.setFieldString(field);

    }



    // Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public int getFieldColor() {
        return fieldColor;
    }

    public int getFieldString() {
        return fieldString;
    }

    public int getYear() {
        return year;
    }

    public String getMajor() {
        return major;
    }

    public int getGrade() {
        return grade;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public String getCompleteAnswer() {
        return CompleteAnswer;
    }


    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setFieldColor(int fieldColor) { this.fieldColor = fieldColor; }

    public void setFieldString(int fieldString) {
        this.fieldString = fieldString;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public void setCompleteAnswer(String completeAnswer) {
        CompleteAnswer = completeAnswer;
    }
}
