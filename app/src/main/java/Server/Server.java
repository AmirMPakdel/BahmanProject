package Server;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import Utils.Consts;
import Utils.log;

public class Server {

    public static void ServerTest(){

        try {
            Volley.POST(Consts.SERVER_TEST,

                    new JSONObject("{data:{hello:'hello'}}"),

                    new OnResponse() {
                        @Override
                        public void onResponse(JSONObject response, int resultCode) {

                            log.print("Reached the sever!");
                        }

                        @Override
                        public void onError(String error) {

                            throw new NullPointerException("No way to reach the server! : "+error);
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
