package abmt2021.exercise.week2.javaIntro;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaghog created on 23.09.2021
 * @project MatsimClass
 */
public class ExerciseOne {

    public static void main (String[] args) {



        System.out.println(args[0]);

        /*right type specification
		int a = 7.5; //would not compile
		*/

        //Careful with integer division
        int a = 5/7;
        System.out.println(a);

        //casting - converting from one type to another
        double b = 5/7; // gives an int? Be careful!! This is an upcast, forcing an int to double, wrong result
        System.out.println(b);

        //to get the result cast the value to a double

        b = 7.0/5;
        System.out.println(b);

        b = (double) 7/5; //this also works
        System.out.println(b);

        //a simple mistake
        b = (double) (a/b); //don't cast this way
        System.out.println(b);

        //downcast from wider to narrower - can cause data loss

        //type casting, one can force it
        int c = (int) 12L;  //12L is of long type, can work as an int so forcing it is okay

        //Reference types example
        Object Ob = new Object ();
        System.out.println(Ob); //prints the address of the reference type variable

        //String declaration
        String name = new String("Grace");
        String name2 = "Grace";

        //Example string methods
        System.out.println(name.substring(2));
        System.out.println(name.length());

        //Comparing strings or other objects
        System.out.println(name == name2); //wrong! this compares the addresses

        System.out.println(name.equals(name2));


        //Arrays
        //Declaring an array
        String [] wordList = new String[10];

        wordList[0] = "ETH";
        //arrayTest[10] ="2021"; //won't compile

        //Fill all indices of the array with "ETH"
        for (int i = 1; i < 10; i++ ){
            wordList[i] = "ETH";
        }

        System.out.println(wordList[5]);

        //other declaration and initialization of array
        int[] myArray = {1,2,3,4,5};

        //ArrayList
        ArrayList<String> myList = new ArrayList<>();
        myList.add("ETH");
        List myList2 = new ArrayList<Integer>(); //note you cannot instantiate a list. E.g List myList2 = new List<Integer>() won't work



    }
}
