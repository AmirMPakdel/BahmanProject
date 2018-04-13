package Authentication;

import android.support.annotation.Nullable;

import Security.EmailValidation;

public class Registration {

    public void signUp_guest(){

        //TODO:: send request -> GET method

        //TODO:: save -> guest_id

        //TODO:: send json ->{'guest_id':'...', 'guest_field':'...', 'guest_grade':'...'}

        //TODO:: wait for ->{'result':'success'}
    }

    public void signUp(String username, String password, String city_id, String school_id, @Nullable String email, @Nullable String phone_number){

        //TODO:: send json ->{'username':'...', 'password':'...', 'city_id':'...', 'school_id':'...', 'email':'...', 'phone_number':'...'}

        //TODO:: wait for ->{'token':'...'}

        //TODO:: save -> token
    }

    public void signIn(String username_or_email, String password){

        boolean is_email = new EmailValidation().isEmailValid(username_or_email);

        if(is_email){

            //TODO:: send json ->{'email':'...', 'password':'...'}
        }else {

            //TODO:: send json ->{'username':'...', 'password':'...'}
        }

        //TODO:: wait for -> {'token':'...'}

        //TODO:: save -> token
    }
}
