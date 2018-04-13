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

import Security.AES;
import Security.KeyAndIV;
import Security.RSA;
import Server.Volley.interfaces.OnResponse;

public class Volley
{
    public static void POST_Encrypted(String url, final JSONObject object, final OnResponse serverResponse)
    {
        if (serverResponse == null)
        {
            throw new NullPointerException("OnResponse can not be null");
        }

        String base64EncryptedData;
        String base64EncryptedKeyAndIV;
        final JSONObject finalObject = new JSONObject();
        final KeyAndIV keyAndIV = new KeyAndIV();

        //region encrypt RawData with AES
        try
        {
            base64EncryptedData = AES.encryptToBase64(keyAndIV, object.toString().getBytes("utf-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Couldn't Encrypt Data");
        }
        //endregion

        //region encrypt Key and IV with AES
        base64EncryptedKeyAndIV = keyAndIV.combineAndEncrypt();
        //endregion

        //region create final POST body
        try
        {
            finalObject.put("isEncrypted" ,true);
            finalObject.put("key", base64EncryptedKeyAndIV);
            finalObject.put("message", base64EncryptedData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //endregion

        //region create request
        StringRequest request = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                JSONObject obj;

                try
                {
                    byte[] decryptedData = AES.decryptFromBase64String(keyAndIV,response);
                    if(decryptedData == null)
                    {
                        throw new NullPointerException("failed to decrypt data coming from server");
                    }
                    response = new String(decryptedData,"utf-8");

                    obj = new JSONObject(response);
                    serverResponse.onResponse(obj);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                serverResponse.onError(error.toString());
            }
        })

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> param = new HashMap<>();
                param.put("data" , finalObject.toString());
                return param;
            }
        };
        //endregion

        VolleySingleton.getInstance().addToRequestQueue(request);
    }
}
