package Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import java.util.List;

import Archives.*;
import Models.Chest;


public class FragmentArchives extends Fragment {


    public FragmentArchives() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_menu_archives, container, false);

        // set the title
        TextView title = view.findViewById(R.id.toolbar_title);
        title.setText("صندوق های تست من");
        title.setTypeface(MainActivity.myFont);

        List<Chest> chests = MainActivity.storageLite.getChestsList();

        ChestRecycleViewAdapter chestRecycleViewAdapter = new ChestRecycleViewAdapter(getContext(), chests, getResources());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_archive_chest);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        recyclerView.setAdapter(chestRecycleViewAdapter);


        return view;
    }

}
