package Archives;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import Models.Field;
import Models.Test;


public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder>{

    private Context context;

    private List<Test> testList;

    public TestRecyclerViewAdapter(Context context, List<Test> testList){

        this.context = context;
        this.testList = testList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(context).inflate(R.layout.recyclerview_test_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        switch (testList.get(position).getQuestion()){

            case (Field.Omoumi.ADABIAT):

                break;

            case (Field.ARABI):

                break;
            case (Field.DINI):

                break;
            case (Field.ENGELISI):

                break;
            case (Field.RIAZI):

                break;
            case (Field.SHIMI):

                break;
            case (Field.ARABI):

                break;
            case (Field.ARABI):

                break;
            case (Field.ARABI):

                break;

        }

        holder.test_name.setText(testList.get(position).getQuestion());

        holder.field_name.setText(testList.get(position).getQuestion().substring(0,200));
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
