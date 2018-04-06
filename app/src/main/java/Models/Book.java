package Models;


import com.blackcoin.packdel.bahmanproject.R;

import java.util.ArrayList;
import java.util.List;

public class Book {


    public class Omoumi{

        public static final String ADABIAT = "adabiat";

        public static final String ARABI = "arabi";

        public static final String DINI = "dini";

        public static final String ENGELISI = "engilisi";
    }

    public class Riazi{

        public static final String RIAZI = "riazi";

        public static final String FIZIK = "fizik";

        public static final String SHIMI = "shimi";
    }


    public class Tajrobi{

        public static final String ZAMINSHENASI = "zaminshenasi";

        public static final String RIAZI = "riazi";

        public static final String ZISTSHENASI = "zistshenasi";

        public static final String FIZIK = "fizik";

        public static final String SHIMI = "shimi";
    }


    public class Ensani {

        public static final String RIAZI = "riazi";

        public static final String EGHTESAD = "eghtesad";

        public static final String ADABIAT = "adabiat";

        public static final String ARABI = "arabi";

        public static final String TARIKH = "tarikh";

        public static final String JOGHRAFIA = "joghrafia";

        public static final String Ejtemaie = "ejtemaie";

        public static final String FALSAFE = "falsafe";

        public static final String MANTEGH = "mantegh";

        public static final String RAVANSHENASI = "ravanshenasi";

    }

    public class Honar{

        public static final String DARKEHONAR = "darkehonar";

        public static final String RIAZIFIZIK = "riazifizik";

        public static final String TARSIMEFANI = "tarsimefani";

        public static final String KHALAGHIATETASVIRI = "khalaghiatetasviri";

        public static final String KHALAGHIATENAMAYESHI = "khalaghiatenamayeshi";

        public static final String KHALAGHIATEMOSIGHI = "khalaghiatemosighi";

        public static final String KHAVASEMAVAD = "khavasemavad";
    }

    public class Colors{

        public static final int ADABIAT = R.color.OmoumiADABIAT;

        public static final int ARABI = R.color.OmoumiARABI;

        public static final int DINI = R.color.OmoumiDINI;

        public static final int ENGELISI = R.color.OmoumiENGELISI;

        public static final int RIAZI = R.color.EnsaniRIAZI;

        public static final int FIZIK = R.color.RiaziFIZIK;

        public static final int SHIMI = R.color.RiaziSHIMI;

        public static final int ZAMINSHENASI = R.color.TajrobiZAMINSHENASI;

        public static final int ZISTSHENASI = R.color.TajrobiZISTSHENASI;

        public static final int EGHTESAD = R.color.EnsaniEGHTESAD;

        public static final int TARIKH = R.color.EnsaniTARIKH;

        public static final int JOGHRAFIA = R.color.EnsaniJOGHRAFIA;

        public static final int Ejtemaie = R.color.EnsaniEjtemaie;

        public static final int FALSAFE = R.color.EnsaniFALSAFE;

        public static final int MANTEGH = R.color.EnsaniMANTEGH;

        public static final int RAVANSHENASI = R.color.EnsaniRAVANSHENASI;

        public static final int DARKEHONAR = R.color.HonarDARKEHONAR;

        public static final int RIAZIFIZIK = R.color.HonarRIAZIFIZIK;

        public static final int TARSIMEFANI = R.color.HonarTARSIMEFANI;

        public static final int KHALAGHIATETASVIRI = R.color.HonarKHALAGHIATETASVIRI;

        public static final int KHALAGHIATENAMAYESHI = R.color.HonarKHALAGHIATENAMAYESHI;

        public static final int KHALAGHIATEMOSIGHI = R.color.HonarKHALAGHIATEMOSIGHI;

        public static final int KHAVASEMAVAD = R.color.HonarKHAVASEMAVAD;

    }

    public class Strings{

        public static final int ADABIAT = R.string.OmoumiADABIAT;

        public static final int ARABI = R.string.OmoumiARABI;

        public static final int DINI = R.string.OmoumiDINI;

        public static final int ENGELISI = R.string.OmoumiENGELISI;

        public static final int RIAZI = R.string.EnsaniRIAZI;

        public static final int FIZIK = R.string.RiaziFIZIK;

        public static final int SHIMI = R.string.RiaziSHIMI;

        public static final int ZAMINSHENASI = R.string.TajrobiZAMINSHENASI;

        public static final int ZISTSHENASI = R.string.TajrobiZISTSHENASI;

        public static final int EGHTESAD = R.string.EnsaniEGHTESAD;

        public static final int TARIKH = R.string.EnsaniTARIKH;

        public static final int JOGHRAFIA = R.string.EnsaniJOGHRAFIA;

        public static final int Ejtemaie = R.string.EnsaniEjtemaie;

        public static final int FALSAFE = R.string.EnsaniFALSAFE;

        public static final int MANTEGH = R.string.EnsaniMANTEGH;

        public static final int RAVANSHENASI = R.string.EnsaniRAVANSHENASI;

        public static final int DARKEHONAR = R.string.HonarDARKEHONAR;

        public static final int RIAZIFIZIK = R.string.HonarRIAZIFIZIK;

        public static final int TARSIMEFANI = R.string.HonarTARSIMEFANI;

        public static final int KHALAGHIATETASVIRI = R.string.HonarKHALAGHIATETASVIRI;

        public static final int KHALAGHIATENAMAYESHI = R.string.HonarKHALAGHIATENAMAYESHI;

        public static final int KHALAGHIATEMOSIGHI = R.string.HonarKHALAGHIATEMOSIGHI;

        public static final int KHAVASEMAVAD = R.string.HonarKHAVASEMAVAD;
    }

    public static int setFieldColor(String field){

        switch (field){

            case Omoumi.ADABIAT:
                return Colors.ADABIAT;

            case Omoumi.ARABI:
                return Colors.ARABI;

            case Omoumi.DINI:
                return Colors.DINI;

            case Omoumi.ENGELISI:
                return Colors.ENGELISI;

            //////////////////////

            case Riazi.RIAZI:
                return Colors.RIAZI;

            case Riazi.FIZIK:
                return Colors.FIZIK;

            case Riazi.SHIMI:
                return Colors.SHIMI;

            //////////////////////

            case Tajrobi.ZAMINSHENASI:
                return Colors.ZAMINSHENASI;

            case Tajrobi.ZISTSHENASI:
                return Colors.ZISTSHENASI;

            //////////////////////

            case Ensani.EGHTESAD:
                return Colors.EGHTESAD;

            case Ensani.Ejtemaie:
                return Colors.Ejtemaie;

            case Ensani.FALSAFE:
                return Colors.FALSAFE;

            case Ensani.JOGHRAFIA:
                return Colors.JOGHRAFIA;

            case Ensani.MANTEGH:
                return Colors.MANTEGH;

            case Ensani.RAVANSHENASI:
                return Colors.RAVANSHENASI;

            case Ensani.TARIKH:
                return Colors.TARIKH;

            //////////////////////

            case Honar.DARKEHONAR:
                return Colors.DARKEHONAR;

            case Honar.KHALAGHIATEMOSIGHI:
                return Colors.KHALAGHIATEMOSIGHI;

            case Honar.KHALAGHIATENAMAYESHI:
                return Colors.KHALAGHIATENAMAYESHI;

            case Honar.KHALAGHIATETASVIRI:
                return Colors.KHALAGHIATETASVIRI;

            case Honar.KHAVASEMAVAD:
                return Colors.KHAVASEMAVAD;

            case Honar.RIAZIFIZIK:
                return Colors.RIAZIFIZIK;

            case Honar.TARSIMEFANI:
                return Colors.TARSIMEFANI;

        }

        return 0;
    }

    public static int setFieldString(String field) {

        switch (field){
            case Omoumi.ADABIAT:
                return Strings.ADABIAT;

            case Omoumi.ARABI:
                return Strings.ARABI;

            case Omoumi.DINI:
                return Strings.DINI;

            case Omoumi.ENGELISI:
                return Strings.ENGELISI;

            //////////////////////

            case Riazi.RIAZI:
                return Strings.RIAZI;

            case Riazi.FIZIK:
                return Strings.FIZIK;

            case Riazi.SHIMI:
                return Strings.SHIMI;

            //////////////////////

            case Tajrobi.ZAMINSHENASI:
                return Strings.ZAMINSHENASI;

            case Tajrobi.ZISTSHENASI:
                return Strings.ZISTSHENASI;

            //////////////////////

            case Ensani.EGHTESAD:
                return Strings.EGHTESAD;

            case Ensani.Ejtemaie:
                return Strings.Ejtemaie;

            case Ensani.FALSAFE:
                return Strings.FALSAFE;

            case Ensani.JOGHRAFIA:
                return Strings.JOGHRAFIA;

            case Ensani.MANTEGH:
                return Strings.MANTEGH;

            case Ensani.RAVANSHENASI:
                return Strings.RAVANSHENASI;

            case Ensani.TARIKH:
                return Strings.TARIKH;

            //////////////////////

            case Honar.DARKEHONAR:
                return Strings.DARKEHONAR;

            case Honar.KHALAGHIATEMOSIGHI:
                return Strings.KHALAGHIATEMOSIGHI;

            case Honar.KHALAGHIATENAMAYESHI:
                return Strings.KHALAGHIATENAMAYESHI;

            case Honar.KHALAGHIATETASVIRI:
                return Strings.KHALAGHIATETASVIRI;

            case Honar.KHAVASEMAVAD:
                return Strings.KHAVASEMAVAD;

            case Honar.RIAZIFIZIK:
                return Strings.RIAZIFIZIK;

            case Honar.TARSIMEFANI:
                return Strings.TARSIMEFANI;
        }

        return 0;
    }

    public List<String> getBooksList(String field){

        List<String> bookList = new ArrayList<>();
        bookList.add(Omoumi.ADABIAT);
        bookList.add(Omoumi.DINI);
        bookList.add(Omoumi.ARABI);
        bookList.add(Omoumi.ENGELISI);

        if(field.equals(Field.RIAZI_FIELD)){

            bookList.add(Riazi.RIAZI);
            bookList.add(Riazi.FIZIK);
            bookList.add(Riazi.SHIMI);
            return bookList;

        }else if (field.equals(Field.TAJROBI_FIELD)){

            bookList.add(Tajrobi.ZAMINSHENASI);
            bookList.add(Tajrobi.RIAZI);
            bookList.add(Tajrobi.ZISTSHENASI);
            bookList.add(Tajrobi.FIZIK);
            bookList.add(Tajrobi.SHIMI);
            return bookList;

        }else if (field.equals(Field.ENSANI_FIELD)){

            bookList.add(Ensani.ADABIAT);
            bookList.add(Ensani.ARABI);
            bookList.add(Ensani.EGHTESAD);
            bookList.add(Ensani.Ejtemaie);
            bookList.add(Ensani.FALSAFE);
            bookList.add(Ensani.JOGHRAFIA);
            bookList.add(Ensani.MANTEGH);
            bookList.add(Ensani.RAVANSHENASI);
            bookList.add(Ensani.RIAZI);
            bookList.add(Ensani.TARIKH);
            return bookList;

        }else if (field.equals(Field.HONAR_FIELD)){

            bookList.add(Honar.DARKEHONAR);
            bookList.add(Honar.KHALAGHIATEMOSIGHI);
            bookList.add(Honar.KHALAGHIATENAMAYESHI);
            bookList.add(Honar.KHALAGHIATETASVIRI);
            bookList.add(Honar.KHAVASEMAVAD);
            bookList.add(Honar.RIAZIFIZIK);
            bookList.add(Honar.TARSIMEFANI);
            return bookList;
        }

        return null;
    }
}
