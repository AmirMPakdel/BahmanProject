package Archives;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;
import com.blackcoin.packdel.bahmanproject.TestActivity;

import java.util.List;

import Models.Book;
import Models.Test;


public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder>{

    private Context context;

    private Resources resources;

    private View view;

    private List<Test> testList;

    public TestRecyclerViewAdapter(Context context, List<Test> testList, Resources getResources){

        this.context = context;
        this.testList = testList;
        this.resources = getResources;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_test_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // setting the color and font
        holder.test_number_paernt.setCardBackgroundColor(resources.getColor(Book.setFieldColor(testList.get(position).getField())));
        holder.test_number.setText(String.valueOf(position+1));
        holder.test_number.setTypeface(MainActivity.myFont);
        holder.test_name.setTypeface(MainActivity.myFont);

        // TODO -> find a better way to show a brief sign of the test
        if(testList.get(position).getQuestion().length() >= 20){

            String s = testList.get(position).getQuestion().substring(0,20);
            s += "...";
            holder.test_name.setText(s);
        }else{
            holder.test_name.setText(testList.get(position).getQuestion());
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
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DELETE", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO -> edit the selected test
        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "EDIT", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CardView test_number_paernt;
        TextView test_number;
        TextView test_name;
        ImageView delete_btn;
        ImageView edit_btn;

        private MyViewHolder(View itemView) {
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
