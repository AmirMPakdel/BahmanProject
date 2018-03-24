package Menu;


import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;

import Archives.*;
import DataFaker.TestFaker;
import Models.Chest;
import Models.Field;


public class FragmentArchives extends Fragment {


    public FragmentArchives() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_menu_archives, container, false);

        // set the title
        TextView title = (TextView) view.findViewById(R.id.toolbar_title);
        title.setText("صندوق های تست من");
        title.setTypeface(MainActivity.myFont);

        List<Chest> chests = new ArrayList<>();

        chests.add(new Chest(Field.Omoumi.ADABIAT, 20, 18));
        chests.add(new Chest(Field.Omoumi.ARABI, 20, 18));
        chests.add(new Chest(Field.Omoumi.DINI, 20, 18));
        chests.add(new Chest(Field.Omoumi.ENGELISI, 20, 18));
        chests.add(new Chest(Field.Riazi.RIAZI, 20, 18));
        chests.add(new Chest(Field.Riazi.FIZIK, 20, 18));
        chests.add(new Chest(Field.Riazi.SHIMI, 20, 18));


        ChestRecycleViewAdapter chestRecycleViewAdapter = new ChestRecycleViewAdapter(getContext(), chests, getResources());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_archive_chest);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        recyclerView.setAdapter(chestRecycleViewAdapter);


        return view;
    }

}
