package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;

import Archives.TestRecyclerViewAdapter;
import DataFaker.TestFaker;


public class ChestDialog extends Dialog {

    public ChestDialog(@NonNull Context context) {
        super(context);
    }

    public ChestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChestDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setup(){

        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_chest);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView orderBy_title = findViewById(R.id.title_orderBy);
        orderBy_title.setTypeface(MainActivity.myFont);
        orderBy_title.setText("ترتیب :");

        Spinner orderBy_spinner = findViewById(R.id.tartib_spinner);

        List<String> orderBy_list = new ArrayList<>();
        orderBy_list.add("اسم");
        orderBy_list.add("تاریخ");
        orderBy_list.add("شماره");
        orderBy_list.add("درس");
        orderBy_list.add("دشواری");

        ArrayAdapter<String> orderBy_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, orderBy_list);

        orderBy_spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        orderBy_spinner.setAdapter(orderBy_spinnerAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = findViewById(R.id.archives_test_recyclerView);

        recyclerView.setLayoutManager(mLayoutManager);

        TestRecyclerViewAdapter testRecyclerViewAdapter = new TestRecyclerViewAdapter(getContext(), TestFaker.getFakeTest(60), getContext().getResources());

        recyclerView.setAdapter(testRecyclerViewAdapter);


        show();
    }
}
