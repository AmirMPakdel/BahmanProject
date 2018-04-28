package Authentication;

import android.support.annotation.Nullable;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Authentication.interfaces.OnGuestSignUpResult;
import Authentication.interfaces.OnSignInResult;
import Authentication.interfaces.OnSignUpResult;
import Models.Guest;
import Security.EmailValidation;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import Utils.Consts;
import Utils.log;

public class Registration {

    public void signUp_guest(final OnGuestSignUpResult onGuestSignUpSuccess){

        //TODO:: send request -> GET method
        Volley.GET(Consts.Registration_signup_guest, new OnResponse() {
            @Override
            public void onResponse(JSONObject response, int resultCode) {

                if(resultCode == Consts.Success){

                    //region save id and send guest data
                    try {

                        //TODO:: save -> GUEST_ID
                        String guest_id = response.getString(Consts.GUEST_ID);

                        Guest guest = MainActivity.storageBox.loadGuest();

                        guest.setId(guest_id);

                        MainActivity.storageBox.saveGuest(guest);


                        //TODO:: send json ->{'GUEST_ID':'...', 'guest_field':'...', 'guest_grade':'...'}
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("GUEST_ID", guest.getId());
                        jsonObject.put("guest_field", guest.getField());
                        jsonObject.put("guest_grade", guest.getGrade());

                        Volley.POST_Encrypted(Consts.Registration_signup_guest, jsonObject, new OnResponse() {
                            @Override
                            public void onResponse(JSONObject response, int resultCode) {

                                //TODO:: wait for ->{'result':'success'}
                                if(resultCode == Consts.Success){

                                    onGuestSignUpSuccess.onSuccess();

                                }else{

                                    onGuestSignUpSuccess.onFailure();
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



    public void signUp(String username, String password, String city_id, @Nullable String email, @Nullable String phone_number, final OnSignUpResult onSignUpResult) throws JSONException {

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

                        MainActivity.storageBox.saveToken(token);

                        onSignUpResult.onSuccess();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{

                    onSignUpResult.onFailure();
                }
            }

            @Override
            public void onError(String error) {

                log.print(error);
            }
        });

        //endregion


    }



    public void signIn(String username_or_email, String password, final OnSignInResult onSignInResult) throws JSONException {

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
                        MainActivity.storageBox.saveToken(response.getString(Consts.TOKEN));

                        onSignInResult.onSuccess();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {

                    onSignInResult.onFailure();
                }
            }

            @Override
            public void onError(String error) {

                log.print(error);
            }
        });
        //endregion
    }
}
