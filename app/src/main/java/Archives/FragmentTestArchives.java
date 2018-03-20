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

        Spinner field_spinner = (Spinner) view.findViewById(R.id.dars_spinner);

        List<String> field_list = new ArrayList<>();
        field_list.add("همه دروس");
        field_list.add("ادبیات");
        field_list.add("عربی");
        field_list.add("دینی");
        field_list.add("انگلیسی");
        field_list.add("ریاضی");
        field_list.add("فیزیک");
        field_list.add("شیمی");

        ArrayAdapter<String> field_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, field_list);

        field_spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        field_spinner.setAdapter(field_spinnerAdapter);

        Spinner orderBy_spinner = (Spinner) view.findViewById(R.id.tartib_spinner);

        List<String> orderBy_list = new ArrayList<>();
        orderBy_list.add("اسم");
        orderBy_list.add("تاریخ");
        orderBy_list.add("شماره");
        orderBy_list.add("درس");
        orderBy_list.add("دشواری");

        ArrayAdapter<String> orderBy_spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, orderBy_list);

        orderBy_spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        orderBy_spinner.setAdapter(orderBy_spinnerAdapter);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.archives_test_recyclerView);



        return view;
    }

}
