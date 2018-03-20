package Models;


import java.util.List;

public class Test {

    private String id;

    private String dars; // math or physics or eng or ...

    private int year; // if it's Konkurs then which year?

    private String major; // mathematics major or biology major or arts major or ...

    private int grade; // 1st grade or 2nd grade ... 4th grade

    private int difficulty; // from 1 to 5, 5 is the hardest

    private String question; // question body

    private List<String> possibleAnswers; // there can be 4 possible answer

    private String CorrectAnswer;

    private String CompleteAnswer; // answer with information and formula

    public Test(String id, String dars, int year, String major, int grade, int difficulty, String question, List<String> possibleAnswers, String correctAnswer, String completeAnswer) {
        this.id = id;
        this.dars = dars;
        this.year = year;
        this.major = major;
        this.grade = grade;
        this.difficulty = difficulty;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        CorrectAnswer = correctAnswer;
        CompleteAnswer = completeAnswer;
    }


    // Getters

    public String getId() {
        return id;
    }

    public String getDars() {
        return dars;
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

    public void setDars(String dars) {
        this.dars = dars;
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
