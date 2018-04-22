package Server.Volley.interfaces;

import org.json.JSONObject;

public interface OnResponse
{
    void onResponse(JSONObject response, int resultCode);
    void onError(String error);
}
