package Utils;

import java.util.ArrayList;
import java.util.List;

public class Consts {

    // storage build with Shared Preferences
    public class StorageSH {

        public static final String PREFS_NAME_RSA = "rsapk";
        public static final String KEY_RSA_PUBLIC_KEY = "rsapublickey";
        public static final String KEY_RSA_LAST_FETCH_DATE = "rsadate";
    }

    // urls
    public static String Registration_signup_guest = "http://172.16.22.199:8000/api/guest/register/";
    public static String Registration_signup = "http://172.16.22.199:8000/api/user/register/";
    public static String Registration_signin = "http://172.16.22.199:8000/api/user/login/";
    public static String Registration_signup_city = "http://172.16.22.199:8000/api/user/register/city";


    // resultCodes
    public static int Success = 1000;


    // json string
    public static String GUEST_ID = "GUEST_ID";
    public static String TOKEN = "token";

    // StorageBox AES Key
    public static String StorageBoxKEY = "AWP";

    // Chest Capacity
    public static int NEW_CHEST_CAPACITY = 10;

    // state list
    public static String[] state_list = {
            "تهران",
            "آذرباییجان غربی",
            "آذرباییجان شرقی",
            "اردبیل",
            "گیلان",
            "مازندران",
            "گلستان",
            "خراسان شمالی",
            "خراسان رضوی",
            "خراسان جنوبی",
            "سمنان",
            "اصفهان",
            "کرمان",
            "فارس",
            "یزد",
            "سیستان بلوچستان",
            "هرمزگان",
            "قم",
            "البرز",
            "کردستان",
            "بوشهر",
            "خوزستان",
            "لرستان",
            "کرمانشاه",
            "قزوین",
            "زنجان",
            "همدان",
            "کهکلویه و بویراحمد",
            "چهارمحال بختیاری",
            "ایلام",
            "مرکزی"};
}
