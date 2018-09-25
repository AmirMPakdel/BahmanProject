package Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import Animation.ClickAnimation;
import Authentication.Registration;
import Authentication.interfaces.OnRegestrationResult;
import Models.Field;
import Models.Grade;
import Storage.StorageBase;
import Storage.StorageBox;
import Toolbar.MenuToolbar;
import Utils.Font;
import Utils.log;


public class FieldChoosingDialog extends Dialog {

    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;

    public FieldChoosingDialog(@NonNull Context context, Activity activity, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    public FieldChoosingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FieldChoosingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setup(){

        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_field_choosing);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);

        //change the font
        TextView dialog_title = findViewById(R.id.dialog_title);
        TextView field_txt = findViewById(R.id.field_txt);
        TextView grade_txt = findViewById(R.id.grade_txt);
        TextView accept_txt = findViewById(R.id.accept_txt);

        dialog_title.setTypeface(Font.myFont);
        field_txt.setTypeface(Font.myFont);
        grade_txt.setTypeface(Font.myFont);
        accept_txt.setTypeface(Font.myFont);


        // Setup the Field Spinner
        List<String> fieldList = new Field().getFieldsList(getContext().getResources());

        ArrayAdapter<String> fieldSpinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, fieldList);

        fieldSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);

        final Spinner fieldSpinner = findViewById(R.id.spinner_field);

        fieldSpinner.setAdapter(fieldSpinnerAdapter);

        // Setup the Grade Spinner
        List<String> gradeList = new Grade().getGradeList(getContext().getResources());

        ArrayAdapter<String> gradeSpinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, gradeList);

        gradeSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);

        final Spinner gradeSpinner = findViewById(R.id.spinner_grade);

        gradeSpinner.setAdapter(gradeSpinnerAdapter);

        // Setup the accept btn
        CardView accept_btn = findViewById(R.id.accept_btn);

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickAnimation.clickBounce(v);

                // save the info and dismiss
                final String grade = new Models.Grade().getGradesNameSpinner(gradeSpinner, getContext().getResources());
                final String field = new Models.Field().getFieldNameSpinner(fieldSpinner, getContext().getResources());

                log.print("grade :"+grade+" field : "+field);

                Registration registration = new Registration();
                registration.SignUp_Guest(grade, field, new OnRegestrationResult() {
                    @Override
                    public void onSuccess() {

                        //TODO:: ARG -> init the socket and connect to the server

                        log.print("On Success !!!!!");

                        StorageBox.getInstance().setGrade(grade);

                        StorageBox.getInstance().setField(field);

                        StorageBox.getInstance().setIsGuest(true);

                        StorageBox.getInstance().setIsFirstTime(false);

                        // Setup MenuToolbar
                        new MenuToolbar(activity.findViewById(R.id.relativeLayout), fragmentManager).setup();

                        // setting the chests for this field
                        StorageBase.getInstance().CreateChests();

                        dismiss();
                    }

                    @Override
                    public void onFailure() {

                        Toast.makeText(getContext(), "Couldn't register as guest!", Toast.LENGTH_LONG).show();

                        //region delete if server is available

                        RealmObjects.SharedPreferences sp = new RealmObjects.SharedPreferences(true);

                        sp.setField(field);
                        sp.setGrade(grade);
                        StorageBox.getInstance().updateSharedPreferences(sp, false, true);

                        // Setup MenuToolbar
                        new MenuToolbar(activity.findViewById(R.id.relativeLayout), fragmentManager).setup();

                        // setting the chests for this field
                        StorageBase.getInstance().CreateChests();

                        dismiss();
                        //endregion
                    }
                });
            }
        });

        // Show the Dialog
        show();
    }
}
