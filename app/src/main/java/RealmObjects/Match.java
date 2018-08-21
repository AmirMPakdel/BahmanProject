package RealmObjects;

import io.realm.RealmObject;

public class Match extends RealmObject {

    private String MyName;

    private String opponentName;

    private byte[] MyPic;

    private byte[] OpponentPic;

    /* insert the image in database



     */

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
}
