package Menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackcoin.packdel.bahmanproject.QuickGameActivity;
import com.blackcoin.packdel.bahmanproject.R;
import com.wang.avi.AVLoadingIndicatorView;

import Animation.ToolbarAnimation;
import Dialogs.RegistrationDialog;
import Storage.StorageBox;


public class FragmentHome extends Fragment {

    private boolean start = true;
    private Button btn_start_match, btn_cancel_match, btn_registration;
    private TextView tv_match_status;
    private AVLoadingIndicatorView loading;

    public FragmentHome() {
    }

    private void initViews(View view) {

        btn_registration = view.findViewById(R.id.registration_btn);

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


        //region TabLayout Animation
        if (start) {
            LinearLayout TabLayout = view.findViewById(R.id.home_tabLayout);
            ToolbarAnimation.TabLayoutAnimate(TabLayout);
            start = false;
        }

        //endregion


        btn_cancel_match.setOnClickListener(v -> {
            btn_start_match.setVisibility(View.VISIBLE);
            btn_start_match.setEnabled(true);
            btn_cancel_match.setVisibility(View.INVISIBLE);
            btn_cancel_match.setEnabled(false);
            tv_match_status.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
        });

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RegistrationDialog(getContext()).setup();

            }
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
