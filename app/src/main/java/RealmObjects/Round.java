package RealmObjects;

import Utils.Consts;
import io.realm.RealmObject;

public class Round extends RealmObject{

    private String match_id;

    private int number;

    private String book;

    private int my_1_test = Consts.Test.TEST_NOT_ANSWERED;

    private int my_2_test = Consts.Test.TEST_NOT_ANSWERED;

    private int op_1_test = Consts.Test.TEST_NOT_ANSWERED;

    private int op_2_test = Consts.Test.TEST_NOT_ANSWERED;

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getMy_1_test() {
        return my_1_test;
    }

    public void setMy_1_test(int my_1_test) {
        this.my_1_test = my_1_test;
    }

    public int getMy_2_test() {
        return my_2_test;
    }

    public void setMy_2_test(int my_2_test) {
        this.my_2_test = my_2_test;
    }

    public int getOp_1_test() {
        return op_1_test;
    }

    public void setOp_1_test(int op_1_test) {
        this.op_1_test = op_1_test;
    }

    public int getOp_2_test() {
        return op_2_test;
    }

    public void setOp_2_test(int op_2_test) {
        this.op_2_test = op_2_test;
    }
}
