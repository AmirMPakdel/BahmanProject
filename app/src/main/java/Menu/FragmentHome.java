package Menu;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.QuickGameActivity;
import com.blackcoin.packdel.bahmanproject.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import Animation.ToolbarAnimation;
import AsyncWorks.LoadQuickGameActivity;
import Dialogs.RegistrationDialog;
import RealmObjects.Match;
import RecycleViews.MatchRecycleViewAdapter;
import RecycleViews.MatchRecyclerView;
import Storage.StorageBase;
import Storage.StorageBox;
import Utils.Font;
import Utils.log;


public class FragmentHome extends Fragment {

    private boolean start = true;
    private FrameLayout btn_start_match, btn_cancel_match;
    private TextView tv_match_status, toolbar_title, txt_start_match, txt_cancel_match, tv_match_loading_status;
    private AVLoadingIndicatorView loading;

    public FragmentHome() {
    }

    private void initViews(View view) {

        toolbar_title = view.findViewById(R.id.toolbar_title);
        tv_match_loading_status = view.findViewById(R.id.tv_match_loading_status);
        txt_start_match = view.findViewById(R.id.quick_match_txt);
        txt_cancel_match = view.findViewById(R.id.cancel_quick_match_txt);
        btn_start_match = view.findViewById(R.id.quick_match_btn);
        btn_cancel_match = view.findViewById(R.id.cancel_quick_match_btn);
        tv_match_status = view.findViewById(R.id.tv_match_loading_status);
        loading = view.findViewById(R.id.match_loading_view);

        btn_start_match.setVisibility(View.VISIBLE);
        btn_start_match.setEnabled(true);
        btn_cancel_match.setVisibility(View.INVISIBLE);
        btn_cancel_match.setEnabled(false);
        tv_match_status.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        loading.show();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

        initViews(view);
        toolbar_title.setTypeface(Font.myFont);
        txt_start_match.setTypeface(Font.myFont);
        txt_cancel_match.setTypeface(Font.myFont);
        tv_match_loading_status.setTypeface(Font.myFont);

        //region TabLayout Animation
        if (start) {
            ConstraintLayout TabLayout = view.findViewById(R.id.home_tabLayout);
            ToolbarAnimation.TabLayoutAnimate(TabLayout);
            start = false;
        }

        //endregion

        //region match recyclerView

        MatchRecyclerView matchRecyclerView = new MatchRecyclerView(getContext());

        matchRecyclerView.setup(view);

        //endregion


        //region setting the initial visibility
        btn_cancel_match.setVisibility(View.INVISIBLE);
        btn_cancel_match.setEnabled(false);
        tv_match_status.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.GONE);
        //endregion

        btn_cancel_match.setOnClickListener(v -> {
            btn_start_match.setVisibility(View.VISIBLE);
            btn_start_match.setEnabled(true);
            btn_cancel_match.setVisibility(View.INVISIBLE);
            btn_cancel_match.setEnabled(false);
            tv_match_status.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
        });


        btn_start_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_start_match.setVisibility(View.INVISIBLE);
                btn_start_match.setEnabled(false);
                btn_cancel_match.setVisibility(View.VISIBLE);
                btn_cancel_match.setEnabled(true);
                tv_match_status.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);

                Intent intent = new Intent(getContext(), QuickGameActivity.class);

                intent.putExtra("match_id", "HELOOOOOOOW");

                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

            }
        });

        return view;
    }

}
