package abmt2021.exercise.week2.javaIntro;

/**
 * @author kaghog created on 27.09.2021
 * @project MatsimClass
 */
public class RunCarShare {
    public static void main(String[] args){

        //create a car object
        CarShare car1 = new CarShare("car!", "mobility", "manual", 4);

        System.out.println(car1.getBookingStatus());
        car1.bookCar();
        System.out.println(car1.getBookingStatus());

        //Testing if our CarShare works so far
        CarShare volvo = new CarShare ("volvo234", "mobility", "manual", 5);
        System.out.println(volvo); //showing that it stores the address as a reference type

        //Try it!!!
        //System.out.println(volvo.id); //not accessible because it is a private field.

        System.out.println(volvo.getId());
        System.out.println(volvo.getBookingStatus());

        //you can test for the other accessors and mutators we created


        //Let's book a car
        volvo.bookCar();
        System.out.println(volvo.getBookingStatus());

        //we have travelled 5km and we want to find out the cost of renting the car
        double distance = 5;
        System.out.println(volvo.tripcost(distance));

    }
}
