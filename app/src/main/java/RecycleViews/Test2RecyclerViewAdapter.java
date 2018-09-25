package RecycleViews;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.R;
import com.blackcoin.packdel.bahmanproject.TestActivity;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import Models.Book;
import Models.Test;
import Utils.Font;

public class Test2RecyclerViewAdapter extends BaseItemDraggableAdapter<Test, Test2RecyclerViewAdapter.MyBaseViewHolder> {

    private View view;

    private Context context;

    private Resources resources;


    public Test2RecyclerViewAdapter(Context context, int layoutResId, @Nullable List<Test> data) {
        super(layoutResId, data);
        this.context = context;
        this.resources = context.getResources();
    }

    @Override
    protected void convert(MyBaseViewHolder helper, Test item) {

        view = helper.itemView;
        int position = helper.getLayoutPosition();

        // setting the color and font
        helper.test_number_paernt.setCardBackgroundColor(resources.getColor(Book.setFieldColor(item.getField())));
        helper.test_number.setText(String.valueOf(position+1));
        helper.test_number.setTypeface(Font.myFont);
        helper.test_name.setTypeface(Font.myFont);

        // TODO -> find a better way to show a brief sign of the test
        if(item.getQuestion().length() >= 20){

            String s = item.getQuestion().substring(0,20);
            s += "...";
            helper.test_name.setText(s);
        }else{
            helper.test_name.setText(item.getQuestion());
        }

        // TODO -> show the whole the test in a TestActivity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestActivity.class);
                context.startActivity(intent);
            }
        });

        // TODO -> delete the selected test
        helper.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DELETE", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO -> edit the selected test
        helper.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "EDIT", Toast.LENGTH_SHORT).show();
            }
        });

    }

    class MyBaseViewHolder extends BaseViewHolder {

        CardView test_number_paernt;
        TextView test_number;
        TextView test_name;
        ImageView delete_btn;
        ImageView edit_btn;


        public MyBaseViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            test_number_paernt = itemView.findViewById(R.id.test_number_parent);
            test_number = itemView.findViewById(R.id.test_number);
            test_name = itemView.findViewById(R.id.test_text);
            delete_btn = itemView.findViewById(R.id.test_delete_ico);
            edit_btn = itemView.findViewById(R.id.test_edit_ico);
        }
    }
}
