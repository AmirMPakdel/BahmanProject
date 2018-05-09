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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import Authentication.Registration;
import Authentication.interfaces.OnRegestrationResult;
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
