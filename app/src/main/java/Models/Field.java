package Models;


import android.content.res.Resources;
import android.widget.Spinner;

import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;

public class Field {

    public static String RIAZI_FIELD = "riazi";

    public static String TAJROBI_FIELD = "tajrobi";

    public static String ENSANI_FIELD = "ensani";

    public static String HONAR_FIELD = "honar";

    public static int setFieldColor(String field) {

        return 0;
    }

    public static int setFieldString(String field) {

        return 0;

    }

    static class Strings {

        public static int RIAZI = R.string.Riazi;

        public static int TAJROBI = R.string.Tajrobi;

        public static int ENSANI = R.string.Ensani;

        public static int HONAR = R.string.Honar;

    }

    public List<String> getFieldsList(Resources resources){

        List<String> fieldsList = new ArrayList<>();

        fieldsList.add(resources.getString(Strings.RIAZI));
        fieldsList.add(resources.getString(Strings.TAJROBI));
        fieldsList.add(resources.getString(Strings.ENSANI));
        fieldsList.add(resources.getString(Strings.HONAR));

        return fieldsList;
    }

    public String getFieldName(String farsiString, Resources resources){

        String s = "empty";

        if(farsiString.equals(resources.getString(Strings.RIAZI))){

            return RIAZI_FIELD;

        }else if(farsiString.equals(resources.getString(Strings.TAJROBI))){

            return TAJROBI_FIELD;

        }else if (farsiString.equals(resources.getString(Strings.ENSANI))){

            return ENSANI_FIELD;

        }else if(farsiString.equals(resources.getString(Strings.HONAR))){

            return HONAR_FIELD;
        }

        return s;
    }

    public String getFieldNameSpinner(Spinner spinner, Resources resources){

        String si = spinner.getSelectedItem().toString();

        return getFieldName(si, resources);
    }

}
