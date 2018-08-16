package Server;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import Utils.Consts;
import Utils.log;

public class Server {

    public static Boolean offline_mode = false;

    public static void ServerTest(final Context context){

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

                            Toast.makeText(context, "Cannot reach server!!!",Toast.LENGTH_LONG).show();
                            offline_mode =  true;
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
