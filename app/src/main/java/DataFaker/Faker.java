package DataFaker;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.util.Random;


public class Faker {

    private Random random;

    private Character[] Fa_sentence_char_array;

    private Character[] Fa_word_char_array;

    public Faker(){

        random = new Random();
        Fa_sentence_char_array = new Character[]{' ', 'آ', 'ب', 'پ', 'ت', ' ', 'ث', 'ج', 'چ', 'ح', 'خ', ' ', 'د', 'ذ',' ', 'ر', 'ز', ' ', 'س', 'ش',' ', 'ص', 'ض', ' ', 'ق', 'ف', 'ک',' ', 'ع', 'غ', ' ', 'ه', 'گ', 'ط',' ', 'ظ', ' ', 'و', 'م', 'ن',' ', 'ل', 'ی', ' ', 'آ', 'ی', ' ','م', 'ن', 'ه', 'د', ' ','ر', 'آ', 'م', ' ', 'ی', 'ن', ' '};
        Fa_word_char_array = new Character[]{'آ', 'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز', 'س', 'ش', 'ص', 'ض', 'ق', 'ف', 'ک', 'ع', 'غ', 'ه', 'گ', 'ط', 'ظ', 'و', 'م', 'ن', 'ل', 'ی', 'آ', 'ی', 'م', 'ن', 'ه', 'د', 'ر', 'آ', 'م', 'ی', 'ن'};

    }


    public String getFakeFarsiWord(int size){
        String s ="";
        for(int i=0; i<size; i++){
            int randomNum = random.nextInt(Fa_word_char_array.length-1);
            s += Fa_word_char_array[randomNum];
        }
        return s;
    }
    public String getFakeFarsiWord(int min, int max) {

        if(min<max) {
            int num = max - min;

            int randomNum = random.nextInt(num);

            randomNum += min;

            return getFakeFarsiWord(randomNum);
        }else {
            return "";
        }
    }


    public String getFakeFarsiSentence(int size){
        String s = "";
        for(int i=0; i<size; i++){
            int randomNum = random.nextInt(Fa_sentence_char_array.length-1);
                s += Fa_sentence_char_array[randomNum];
        }
        return s;
    }
    public String getFakeFarsiSentence(int min, int max) {

        if(min<max) {
            int num = max - min;

            int randomNum = random.nextInt(num);

            randomNum += min;
            
            return getFakeFarsiSentence(randomNum);
        }else {
            return "";
        }
    }
}
