package Server.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.blackcoin.packdel.bahmanproject.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Server.Volley.interfaces.OnResponse;

public class Volley
{
    //// TODO: 4/11/18 implement encryption and decryption and do some optimization
    public static void POST(String url, final JSONObject object, final OnResponse serverResponse) {
        if (serverResponse == null) {
            throw new NullPointerException("OnResponse can not be null");
        }

        StringRequest request = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj;
                try {
                    obj = new JSONObject(response);
                    serverResponse.onResponse(obj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverResponse.onError(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("data", object.toString());
                return param;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(request);
    }
}
