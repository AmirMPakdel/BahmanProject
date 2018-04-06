package Models;


import android.content.res.Resources;
import android.widget.Spinner;

import com.blackcoin.packdel.bahmanproject.MainActivity;
import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;

public class Grade {

    public static String FIRST_GRADE = "1st";

    public static String SECOND_GRADE = "2nd";

    public static String THIRD_GRADE = "3rd";

    static class Strings{

        public static int FIRST = R.string.FirstGrade;

        public static int SECOND = R.string.SecondGrade;

        public static int THIRD = R.string.ThirdGrade;
    }

    public List<String> getGradeList(Resources resources){

        List<String> fieldsList = new ArrayList<>();

        fieldsList.add(resources.getString(Strings.FIRST));
        fieldsList.add(resources.getString(Strings.SECOND));
        fieldsList.add(resources.getString(Strings.THIRD));

        return fieldsList;
    }

    public String getGradeName(String farsiString, Resources resources){

        String s = "empty";

        MainActivity.log("farsiString : "+farsiString);
        MainActivity.log("resources : "+resources.getString(Strings.SECOND));

        if(farsiString.equals(resources.getString(Strings.FIRST))){

            return FIRST_GRADE;

        }else if(farsiString.equals(resources.getString(Strings.SECOND))){

            return SECOND_GRADE;

        }else if (farsiString.equals(resources.getString(Strings.THIRD))){

            return THIRD_GRADE;

        }

        return s;
    }

    public String getGradesNameSpinner(Spinner spinner, Resources resources){

        String si = spinner.getSelectedItem().toString();

        MainActivity.log("getGradesNameSpinner : "+getGradeName(si, resources));

        return getGradeName(si, resources);
    }

}
