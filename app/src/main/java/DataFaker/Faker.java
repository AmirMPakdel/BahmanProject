package DataFaker;

import java.util.Random;


public class Faker {

    Character[] Fa_char_array;

    public Faker(){

        Fa_char_array = new Character[]{' ', 'آ', 'ب', 'پ', 'ت', ' ', 'ث', 'ج', 'چ', 'ح', 'خ', ' ', 'د', 'ذ', 'ر', 'ز', ' ', 'س', 'ش', 'ص', 'ض', ' ', 'ق', 'ف', 'ک', 'ع', 'غ', ' ', 'ه', 'گ', 'ط', 'ظ', ' ', 'و', 'م', 'ن', 'ل', 'ی', ' ', 'آ', 'ی', 'م', 'ن', 'ه', 'د', 'ر', 'آ', 'م', ' ', 'ی', 'ن', ' '};


    }

    public String getFakeString(int size){

        String s;

        Character[] c;

        /*for(int i=0; i<size; i++){

            Random random = new Random();

            random.nextInt(Fa_char_array.length-1);
                s+=Fa_char_array[i];
        }*/

        return null;
    }
}
