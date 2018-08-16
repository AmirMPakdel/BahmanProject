package Server.Volley;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import Security.AES;
import Security.KeyAndIV;
import Server.Volley.interfaces.OnResponse;


public class Volley {

    public static void POST_Encrypted(String url, final JSONObject object, final OnResponse serverResponse) {

        if (serverResponse == null) {
            throw new NullPointerException("OnResponse can not be null");
        }

        String base64EncryptedData;
        String base64EncryptedKeyAndIV;
        final JSONObject finalObject = new JSONObject();
        final KeyAndIV keyAndIV = new KeyAndIV();

        //region encrypt RawData with AES
        try {
            base64EncryptedData = AES.encryptToBase64(keyAndIV, object.toString().getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't Encrypt Data");
        }
        //endregion

        //region encrypt Key and IV with AES
        base64EncryptedKeyAndIV = keyAndIV.combineAndEncrypt();
        //endregion

        //region create final POST body
        try {
            finalObject.put("is_encrypted", true);
            finalObject.put("key", base64EncryptedKeyAndIV);
            finalObject.put("message", base64EncryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        //region create request
        StringRequest request = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);


                    if (obj.getBoolean("is_encrypted")) {
                        String encryptedMessage = obj.getString("message");
                        byte[] decryptedData = AES.decryptFromBase64String(keyAndIV, encryptedMessage.substring(2, encryptedMessage.length() - 1));
                        if (decryptedData == null) {
                            throw new NullPointerException("****** failed to decrypt data coming from server *******");
                        }
                        response = new String(decryptedData, "utf-8");
                        serverResponse.onResponse(new JSONObject(response), obj.getInt("result_code"));
                    } else {
                        serverResponse.onResponse(new JSONObject(obj.getString("message")), obj.getInt("result_code"));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                serverResponse.onError("VolleyError : \n" + error.toString() + "\n" + error.getMessage() + "\n" + error.getCause());
            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("data", finalObject.toString());
                return param;
            }
        };
        //endregion

        VolleySingleton.getInstance().addToRequestQueue(request);

    }



    public static void POST(String url, final JSONObject object, final OnResponse serverResponse) {
        if (serverResponse == null)
        {
            throw new NullPointerException("OnResponse can not be null");
        }

        //region creating final JSOn object to POST
        final JSONObject finalObject = new JSONObject();
        try {
            finalObject.put("is_encrypted", false);
            finalObject.put("message", object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        //region create request
        StringRequest request = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj;

                try {
                    obj = new JSONObject(response);
                    serverResponse.onResponse(obj.getJSONObject("message") , obj.getInt("result_code"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverResponse.onError("VolleyError : \n" + error.toString() + "\n" + error.getMessage() + "\n" + error.getCause());
            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("data", finalObject.toString());
                return param;
            }
        };
        //endregion

        VolleySingleton.getInstance().addToRequestQueue(request);
    }



    public static void GET(String url, final OnResponse serverResponse){

        if (serverResponse == null)
        {
            throw new NullPointerException("OnResponse can not be null");
        }

        //region create request
        StringRequest request = new StringRequest(StringRequest.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj;

                try {
                    obj = new JSONObject(response);
                    serverResponse.onResponse(obj.getJSONObject("message") , obj.getInt("result_code"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverResponse.onError("VolleyError : \n" + error.toString() + "\n" + error.getMessage() + "\n" + error.getCause());
            }
        });
        //endregion

        VolleySingleton.getInstance().addToRequestQueue(request);
    }


}
