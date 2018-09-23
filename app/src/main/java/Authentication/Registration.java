package Authentication;

import android.support.annotation.Nullable;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Authentication.interfaces.OnRegestrationResult;
import Models.Guest;
import Security.EmailValidation;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import Storage.StorageBox;
import Utils.Consts;
import Utils.log;

public class Registration {

    public void signUp_guest(final OnRegestrationResult onRegestrationResult){

        //TODO:: send request -> GET method
        Volley.GET(Consts.Registration_signup_guest, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {

                if(resultCode == Consts.Success){

                    //region save id and send guest data
                    try {

                        //TODO:: save -> GUEST_ID
                        String guest_id = response.getString(Consts.GUEST_ID);

                        StorageBox.getInstance().setId(guest_id);

                        //TODO:: send json ->{'GUEST_ID':'...', 'guest_field':'...', 'guest_grade':'...'}
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("GUEST_ID", StorageBox.sharedPreferences.getId());
                        jsonObject.put("guest_field", StorageBox.sharedPreferences.getField());
                        jsonObject.put("guest_grade", StorageBox.sharedPreferences.getGrade());

                        Volley.POST_Encrypted(Consts.Registration_signup_guest, jsonObject, new OnResponse() {
                            @Override
                            public void onResponse(JSONObject response, int resultCode) {

                                //TODO:: wait for ->{'result':'success'}
                                if(resultCode == Consts.Success){

                                    StorageBox.getInstance().setIsGuest(true);

                                    onRegestrationResult.onSuccess();

                                }else{

                                    onRegestrationResult.onFailure();
                                }
                            }

                            @Override
                            public void onError(String error) {

                                log.print(error);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //endregion
                }
            }

            @Override
            public void onError(String error) {

                log.print(error);
            }
        });
    }



    public void signUp(String username, String password, int city_id, @Nullable String email, @Nullable String phone_number, final OnRegestrationResult onRegestrationResult) throws JSONException {

        //region create the json object
        //TODO:: send json ->{'username':'...', 'password':'...', 'city_id':'...', 'email':'...', 'phone_number':'...'}
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("city_id", city_id);
        if(email != null){jsonObject.put("email", email);}
        if(phone_number != null){jsonObject.put("phone_number", phone_number);}
        //endregion

        //region send the data and get the token

        //TODO:: wait for ->{'token':'...'}
        Volley.POST_Encrypted(Consts.Registration_signup, jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {

                if(resultCode == Consts.Success){

                    try {
                        //TODO:: save -> token
                        String token = response.getString("token");

                        StorageBox.getInstance().setToken(token);

                        log.print("token saved : "+token);

                        onRegestrationResult.onSuccess();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{

                    onRegestrationResult.onFailure();
                }
            }

            @Override
            public void onError(String error) {

                log.print(error);
            }
        });

        //endregion


    }



    public void signIn(String username_or_email, String password, final OnRegestrationResult onRegestrationResult) throws JSONException {

        //region create the json object
        boolean is_email = EmailValidation.isEmailValid(username_or_email);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("password", password);

        if(is_email){

            //TODO:: send json ->{'email':'...', 'password':'...'}
            jsonObject.put("email", username_or_email);

        }else {

            //TODO:: send json ->{'username':'...', 'password':'...'}
            jsonObject.put("username", username_or_email);
        }
        //endregion

        //region send data and wait for token
        //TODO:: wait for -> {'token':'...'}
        Volley.POST_Encrypted(Consts.Registration_signin, jsonObject, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {

                //TODO:: save -> token
                if(resultCode == Consts.Success){

                    try {

                        StorageBox.getInstance().setToken(response.getString(Consts.TOKEN));

                        onRegestrationResult.onSuccess();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {

                    onRegestrationResult.onFailure();
                }
            }

            @Override
            public void onError(String error) {

                log.print(error);
            }
        });
        //endregion
    }



    public void SignUp_Guest(final String grade, final String field, final OnRegestrationResult onRegestrationResult){

        Volley.GET(Consts.Registration_signup_guest, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {


                JSONObject jsonObject = new JSONObject();

                try {
                    String guest_id = response.getString("guest_id");
                    jsonObject.put("guest_grade", grade);
                    jsonObject.put("guest_field", field);
                    jsonObject.put("guest_id", guest_id);

                    Volley.POST_Encrypted(Consts.Registration_signup_guest, jsonObject, new OnResponse() {
                        @Override
                        public void onResponse(JSONObject response, int resultCode) {

                            if(resultCode == Consts.Success){

                                onRegestrationResult.onSuccess();

                            }else {
                                onRegestrationResult.onFailure();
                            }
                        }

                        @Override
                        public void onError(String error) {

                            onRegestrationResult.onFailure();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

                onRegestrationResult.onFailure();

            }
        });
    }


}
