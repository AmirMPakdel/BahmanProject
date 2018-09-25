package Server;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Volley.Volley;
import Server.Volley.VolleySingleton;
import Server.Volley.interfaces.OnHttpConnected;
import Server.Volley.interfaces.OnResponse;
import Utils.Consts;
import Utils.log;

public class Server {

    public static Boolean online = false;

    public static void checkConnection(final Context context, OnHttpConnected onHttpConnected) {

        StringRequest request = new StringRequest(Consts.SERVER_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                online = true;

                onHttpConnected.onConnect();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                onHttpConnected.onError();
            }
        });

        VolleySingleton.getInstance().addToRequestQueue(request);
    }
}
