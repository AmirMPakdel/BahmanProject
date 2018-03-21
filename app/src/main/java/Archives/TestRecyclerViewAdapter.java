package Archives;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;
import java.util.List;
import Models.Test;


public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder>{

    private Context context;

    private Resources resources;

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

        holder.field_name.setBackgroundColor(resources.getColor(testList.get(position).getFieldColor()));

        holder.field_name.setText(resources.getString(testList.get(position).getFieldString()));


        if(testList.get(position).getQuestion().length() >= 30){

            String s = testList.get(position).getQuestion().substring(0,30);
            s += "...";
            holder.test_name.setText(s);
        }else{
            holder.test_name.setText(testList.get(position).getQuestion());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView field_name;

        TextView test_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            field_name = (TextView) itemView.findViewById(R.id.test_field);
            test_name = (TextView) itemView.findViewById(R.id.test_text);
        }
    }
}
