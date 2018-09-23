package RecycleViews;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import Animation.ClickAnimation;
import RealmObjects.Match;
import Utils.Converter;
import Utils.Font;
import de.hdodenhof.circleimageview.CircleImageView;

public class MatchRecycleViewAdapter extends RecyclerView.Adapter<MatchRecycleViewAdapter.MyViewHolder> {

    private List<Match> matchList;

    public MatchRecycleViewAdapter(List<Match> matchList) {
        this.matchList = matchList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview_match, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Match match = matchList.get(i);

        myViewHolder.MyName.setText(match.getMyName());

        myViewHolder.OpponentName.setText(match.getOpponentName());

        myViewHolder.MyName.setTypeface(Font.myFont);

        myViewHolder.OpponentName.setTypeface(Font.myFont);

        myViewHolder.MyPic.setImageBitmap(Converter.byteArray2Bitmap(match.getMyPic()));

        myViewHolder.OpponentPic.setImageBitmap(Converter.byteArray2Bitmap(match.getOpponentPic()));


        myViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClickAnimation.clickBounce(myViewHolder.container);
                //TODO:: go to the match's activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout container;

        TextView MyName;

        TextView OpponentName;

        CircleImageView MyPic;

        CircleImageView OpponentPic;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.match_item_container);

            MyName = itemView.findViewById(R.id.myname_tv);

            OpponentName = itemView.findViewById(R.id.opponentName_tv);

            MyPic = itemView.findViewById(R.id.mypic_civ);

            OpponentPic = itemView.findViewById(R.id.opponentpic_civ);
        }
    }

}
