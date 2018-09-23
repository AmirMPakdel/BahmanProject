package AsyncWorks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.blackcoin.packdel.bahmanproject.R;

public class LoadQuickGameActivity extends AsyncTask<Object,String,String>{

    @Override
    protected String doInBackground(Object... objects) {

        Intent i = (Intent) objects[0];

        Activity a = (Activity) objects[1];

        a.startActivity(i);

        a.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        return null;
    }
}
