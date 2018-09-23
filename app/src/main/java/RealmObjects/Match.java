package RealmObjects;

import Utils.Consts;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Match extends RealmObject {

    @PrimaryKey
    private String id;

    private String state = Consts.Match.STATE_BOOK_CHOOSING;

    private String MyName;

    private String opponentName;

    private byte[] MyPic;

    private byte[] OpponentPic;

    public RealmList<Round> roundsList = new RealmList<>();

    private String my_1_book = Consts.Match.BOOK_UNSET;

    private String my_2_book = Consts.Match.BOOK_UNSET;

    private RealmList<String> book_1_choices = new RealmList<>();

    private RealmList<String> book_2_choices = new RealmList<>();

    private String opponent_1_book = Consts.Match.BOOK_UNSET;

    private String opponent_2_book = Consts.Match.BOOK_UNSET;

    public Match() {

    }

    public RealmList<Round> getRoundsList() {
        return roundsList;
    }

    public void setRoundsList(RealmList<Round> roundsList) {
        this.roundsList = roundsList;
    }

    public byte[] getMyPic() {
        return MyPic;
    }

    public void setMyPic(byte[] myPic) {
        MyPic = myPic;
    }

    public byte[] getOpponentPic() {
        return OpponentPic;
    }

    public void setOpponentPic(byte[] opponentPic) {
        OpponentPic = opponentPic;
    }

    public String getMyName() {
        return MyName;
    }

    public void setMyName(String myName) {
        MyName = myName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMy_1_book() {
        return my_1_book;
    }

    public void setMy_1_book(String my_1_book) {
        this.my_1_book = my_1_book;
    }

    public String getMy_2_book() {
        return my_2_book;
    }

    public void setMy_2_book(String my_2_book) {
        this.my_2_book = my_2_book;
    }

    public String getOpponent_1_book() {
        return opponent_1_book;
    }

    public void setOpponent_1_book(String opponent_1_book) {
        this.opponent_1_book = opponent_1_book;
    }

    public String getOpponent_2_book() {
        return opponent_2_book;
    }

    public void setOpponent_2_book(String opponent_2_book) {
        this.opponent_2_book = opponent_2_book;
    }

    public RealmList<String> getBook_1_choices() {
        return book_1_choices;
    }

    public void setBook_1_choices(RealmList<String> book_1_choices) {
        this.book_1_choices = book_1_choices;
    }

    public RealmList<String> getBook_2_choices() {
        return book_2_choices;
    }

    public void setBook_2_choices(RealmList<String> book_2_choices) {
        this.book_2_choices = book_2_choices;
    }
}
