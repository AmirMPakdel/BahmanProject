package RecycleViews;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;


import RealmObjects.Match;
import Storage.StorageBase;

public class MatchRecyclerView {

    Context context;

    public MatchRecyclerView(Context context) {

        this.context = context;
    }

    public void setup(View view){


        List<Match> matches = StorageBase.getInstance().getMatchList();

        MatchRecycleViewAdapter matchRecycleViewAdapter = new MatchRecycleViewAdapter(matches);

        RecyclerView recyclerView = view.findViewById(R.id.match_recycleView);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        recyclerView.setAdapter(matchRecycleViewAdapter);

    }
}
