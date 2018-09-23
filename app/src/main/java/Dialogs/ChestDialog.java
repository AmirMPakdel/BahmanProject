package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

import Archives.Test2RecyclerViewAdapter;
import Archives.TestRecyclerViewAdapter;
import DataFaker.TestFaker;
import Utils.Font;


public class ChestDialog extends Dialog {

    public ChestDialog(@NonNull Context context) {
        super(context);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    public ChestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChestDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setup(){

        this.setContentView(R.layout.dialog_chest);

        TextView orderBy_title = findViewById(R.id.title_orderBy);
        orderBy_title.setTypeface(Font.myFont);
        orderBy_title.setText("ترتیب :");

        Spinner orderBy_spinner = findViewById(R.id.tartib_spinner);

        List<String> orderBy_list = new ArrayList<>();
        orderBy_list.add("اسم");
        orderBy_list.add("تاریخ");
        orderBy_list.add("شماره");
        orderBy_list.add("درس");
        orderBy_list.add("دشواری");

        ArrayAdapter<String> orderBy_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, orderBy_list);

        orderBy_spinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);

        orderBy_spinner.setAdapter(orderBy_spinnerAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = findViewById(R.id.archives_test_recyclerView);

        recyclerView.setLayoutManager(mLayoutManager);


        /*TestRecyclerViewAdapter testRecyclerViewAdapter = new TestRecyclerViewAdapter(getContext(), TestFaker.getFakeTest(60), getContext().getResources());

        recyclerView.setAdapter(testRecyclerViewAdapter);*/

        Test2RecyclerViewAdapter test2RecyclerViewAdapter = new Test2RecyclerViewAdapter(getContext(), R.layout.item_recyclerview_test,TestFaker.getFakeTest(60));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemDragAndSwipeCallback(test2RecyclerViewAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //TODO:: save the position changes
        OnItemDragListener onItemDragListener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) { }
            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) { }
            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) { }
        };
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) { }
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) { }
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) { }
            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) { }
        };

        // open drag
        test2RecyclerViewAdapter.enableDragItem(itemTouchHelper, R.id.test_number_parent, true);
        test2RecyclerViewAdapter.setOnItemDragListener(onItemDragListener);

        // open slide to delete
        test2RecyclerViewAdapter.enableSwipeItem();
        test2RecyclerViewAdapter.setOnItemSwipeListener(onItemSwipeListener);

        recyclerView.setAdapter(test2RecyclerViewAdapter);

        show();
    }
}
