package Archives;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentTestArchives extends Fragment {


    public FragmentTestArchives() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_archives_test, container, false);

        Spinner dars_spinner = (Spinner) view.findViewById(R.id.dars_spinner);

        List<String> dars_list = new ArrayList<>();
        dars_list.add("همه دروس");
        dars_list.add("ادبیات");
        dars_list.add("معارف اسلامی");
        dars_list.add("عربی");
        dars_list.add("انگلیسی");
        dars_list.add("ریاضی");
        dars_list.add("فیزیک");
        dars_list.add("شیمی");

        ArrayAdapter<String> dars_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, dars_list);

        dars_spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        dars_spinner.setAdapter(dars_spinnerAdapter);

        Spinner tartib_spinner = (Spinner) view.findViewById(R.id.tartib_spinner);

        List<String> tartib_list = new ArrayList<>();
        tartib_list.add("اسم");
        tartib_list.add("تاریخ");
        tartib_list.add("شماره");
        tartib_list.add("درس");
        tartib_list.add("دشواری");

        ArrayAdapter<String> tartib_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, tartib_list);

        tartib_spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        tartib_spinner.setAdapter(tartib_spinnerAdapter);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.archives_test_recyclerView);



        return view;
    }

}
