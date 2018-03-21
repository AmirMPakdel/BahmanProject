package DataFaker;

import com.blackcoin.packdel.bahmanproject.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.Field;
import Models.Test;


public class TestFaker {

    public TestFaker(){

    }

    public static Test getFakeTest(){

        Random random = new Random();

        Faker faker = new Faker();

        String id = String.valueOf(random.nextInt());

        String name =  faker.getFakeFarsiWord(5,12);

        List<String> fields = new ArrayList<>();
        fields.add(Field.Omoumi.ADABIAT);
        fields.add(Field.Omoumi.ARABI);
        fields.add(Field.Omoumi.DINI);
        fields.add(Field.Omoumi.ENGELISI);
        fields.add(Field.Riazi.RIAZI);
        fields.add(Field.Riazi.FIZIK);
        fields.add(Field.Riazi.SHIMI);
        String field =  fields.get(random.nextInt(7));

        String question = faker.getFakeFarsiSentence(40,50); // "اگر در مدار مقابل کلید را روشن کنیم مقدار ولتاژ لامپ دوم چقدر است؟"

        MainActivity.log(question);

        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add("1");
        possibleAnswers.add("2");
        possibleAnswers.add("3");
        possibleAnswers.add("4");

        String correctAnswer = String.valueOf(random.nextInt(3));


        Test test = new Test(id, name, field, 98, "Riazi", 3, 4, question, possibleAnswers, correctAnswer, "بلا بلا بلا بلا");
        return test;
    }


    public static List<Test> getFakeTest(int size){

        List<Test> testList = new ArrayList<>();

        for(int i=0; i<size; i++){

            testList.add(getFakeTest());
        }

        return testList;
    }

}
