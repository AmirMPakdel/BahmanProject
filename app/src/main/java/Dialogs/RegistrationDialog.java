package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Authentication.Registration;
import Authentication.interfaces.OnRegestrationResult;
import Server.Volley.Volley;
import Server.Volley.interfaces.OnResponse;
import Utils.Consts;
import Utils.log;

public class RegistrationDialog extends Dialog {


    public RegistrationDialog(@NonNull Context context) {
        super(context);
    }

    public RegistrationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegistrationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public void setup() {

        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_signin);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);

        InSignIn();

        show();
    }

    private void InSignIn(){

        // signin button
        CardView signin_btn = findViewById(R.id.signin_btn);
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(InSignInGetData()){

                    dismiss();

                }else{

                    Toast.makeText(getContext(), "wrong inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // sign-up city spinner
        Spinner city_spinner = findViewById(R.id.city_spinner);

        List<String> city_list = new ArrayList<>();


        // sign-up
        TextView signup_title = findViewById(R.id.signup_title);
        signup_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LayoutInflater factory = getLayoutInflater();

                final View dialog_signup = factory.inflate(R.layout.dialog_signup, null);

                dialog_signup.animate().rotation(360).setDuration(500).start();

                setContentView(dialog_signup);

                InSignUp();
            }
        });

        // exit button
        ImageView exit_btn = findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
    }

    private void InSignUp(){

        // sign-up state spinner
        final Spinner state_spinner = findViewById(R.id.state_spinner);

        List<String> states = new ArrayList<>();

        for(String state : Consts.state_list){ states.add(state); }

        ArrayAdapter<String> state_spinner_addapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner, states);

        state_spinner_addapter.setDropDownViewResource(R.layout.item_spinner_dropdown);

        state_spinner.setAdapter(state_spinner_addapter);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), state_spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), state_spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        // sign-up city spinner
        final Spinner city_spinner = findViewById(R.id.city_spinner);

        final List<String> city_list = new ArrayList<>();

        try {
            Volley.POST(Consts.Registration_signup_city, new JSONObject().put("city", state_spinner.getSelectedItem().toString()), new OnResponse() {
                @Override
                public void onResponse(JSONObject response, int resultCode){

                    try {
                        JSONArray jsonArray = response.getJSONArray("city");

                        for(int i=0; jsonArray.get(i) != null; i++){

                            city_list.add(jsonArray.getString(i));

                        }

                        ArrayAdapter<String> city_spinner_adapter = new ArrayAdapter<>(getContext(),R.layout.item_spinner, city_list);

                        city_spinner_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);

                        city_spinner.setAdapter(city_spinner_adapter);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onError(String error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // signup button
        CardView signun_btn = findViewById(R.id.registration_btn);
        signun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(InSignUpGetData()){

                    dismiss();

                }else{

                    Toast.makeText(getContext(), "wrong inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // back to sign-in
        ImageView back_to_signin = findViewById(R.id.back_btn);
        back_to_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LayoutInflater factory = getLayoutInflater();

                final View dialog_signup = factory.inflate(R.layout.dialog_signin, null);

                dialog_signup.animate().rotation(-360).setDuration(500).start();

                setContentView(dialog_signup);

                InSignIn();
            }
        });

        // exit button
        ImageView exit_btn = findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
    }

    private boolean InSignInGetData() {

        final boolean[] netProblem = {false};

        try {

            EditText username_txEdit = findViewById(R.id.usernameORemail_edit);
            EditText password_txEdit = findViewById(R.id.password_edit);

            final String username = username_txEdit.getText().toString().trim();
            final String password = password_txEdit.getText().toString().trim();

            if(username.equals("") || password.equals("")){
                return false;
            }

            Registration registration = new Registration();
            registration.signIn(username, password, new OnRegestrationResult() {
                @Override
                public void onSuccess() {
                    MainActivity.storageBox.save("username", username);
                    MainActivity.storageBox.save("password", password);
                    log.print("onSucces");
                }

                @Override
                public void onFailure() {

                    log.print("onFailure");
                    Toast.makeText(getContext(), "internet Problem!", Toast.LENGTH_SHORT).show();
                    netProblem[0] = true;
                }
            });


        }catch (Exception e){
            return false;
        }

        return !netProblem[0];

    }

    private boolean InSignUpGetData(){

        final boolean[] netProblem = {false};

        try {

            EditText username_txEdit = findViewById(R.id.usernameORemail_edit);
            EditText password_txEdit = findViewById(R.id.password_edit);
            EditText email_txEdit = findViewById(R.id.email_edit);
            Spinner states_spn = findViewById(R.id.state_spinner);
            Spinner cities_spn = findViewById(R.id.city_spinner);

            final String username = username_txEdit.getText().toString().trim();
            final String password = password_txEdit.getText().toString().trim();
            final String email = email_txEdit.getText().toString().trim();

            if(username.equals("") || password.equals("")){
                return false;
            }

            Registration registration = new Registration();
            registration.signUp(username, password, 123, email, "0911", new OnRegestrationResult() {
                @Override
                public void onSuccess() {
                    MainActivity.storageBox.save("username", username);
                    MainActivity.storageBox.save("password", password);
                    MainActivity.storageBox.save("email", email);
                }

                @Override
                public void onFailure() {

                    Toast.makeText(getContext(), "internet Problem!", Toast.LENGTH_SHORT).show();
                    netProblem[0] = true;
                }
            });


        }catch (Exception e){
            return false;
        }

        return !netProblem[0];
    }

}
