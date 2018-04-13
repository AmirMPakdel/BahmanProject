//// TODO: 4/13/18 this class currently is not functional, might be deleted later


package Server;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blackcoin.packdel.bahmanproject.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Server {

    private String domain;

    public Server(String domain){

        this.domain = domain;
    }

    public void test(Context context){

        String url;// = domain + "signup.php?...";

        url = "http://172.16.22.244:8000/api/user/save/";

        RequestQueue queue = Volley.newRequestQueue(context);

        try {

            JSONObject jsonObject = new JSONObject("{'username':'amirMuhammad', 'password':'helloWorld2123'}");

            JsonObjectRequest JRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    MainActivity.log("respond!");

                    try {

                        MainActivity.log(response.getString("respond"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    MainActivity.log("error");
                    MainActivity.log(error.toString());
                }
            });

            queue.add(JRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
