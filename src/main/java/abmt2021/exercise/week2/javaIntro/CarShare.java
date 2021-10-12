package abmt2021.exercise.week2.javaIntro;

/**
 * @author kaghog created on 27.09.2021
 * @project MatsimClass
 */
public class CarShare {
    private String id;
    private String operator;
    private String type;
    private int paxCapacity;
    private double fuelLevel;
    private boolean bookingStatus;
    private static double fuelPrice = 2; //CHF/L
    private static final boolean LICENCEREQUIRED = true;


    //Constructor
    public CarShare(String id, String operator, String type, int paxCapacity) {
        this.id = id;
        this.operator = operator;
        this.type = type;
        this.paxCapacity = paxCapacity;
        this.fuelLevel = 40;
        this.bookingStatus = false;
    }

    //If all the fields are private we would need to enable some accessors and mutators for information we want to share

    //fields that are read only after creating the object
    public boolean getBookingStatus() {

        return this.bookingStatus;
    }

    public String getId() {
        return id;
    }

    public String getOperator() {
        return operator;
    }

    public String getType() {
        return type;
    }

    public int getPaxCapacity() {
        return paxCapacity;
    }

    //fields that can have read-write option

    public double getFuelPrice(){
        return fuelPrice;
    }

    public void setFuelPrice(double price) {
        //want to ensure that fuel price is a positive integer greater than 0
        if (price > 0) {
            fuelPrice = price;
        }
        else {
            throw new IllegalStateException("Fuel price should be a positive integer: " + price);
        }

    }

    public double getFuelLevel(){
        return fuelLevel;
    }

    public void setFuelLevel(double level) {
        if (level > 0) {
            fuelLevel = level;
        }
        else {
            throw new IllegalStateException("Fuel level should be a positive integer: " + level);
        }

    }

    // you can play around and modify the mutator methods as you want. Maybe add a maximum price or fuel level.

    //Other behaviours we would like to define

    public void bookCar() {
        if(!bookingStatus) {
            bookingStatus = true; //this is same as this.bookingStatus, it is not necessary to specify
        }
        else {
            System.out.println("Car is already booked");
        }
    }

    //Solution to the class exercise
    //create a drive method which returns the fuel level after driving a certain km

    public double drive(double distance) {
        double efficiency= 0.1; // L/km
        double fuelUsage = efficiency * distance;

        //update the fuel level of the car
        fuelLevel -= fuelUsage;

        return fuelUsage; //returns fuel usage
    }

    //Calculate the rental price based on fuel price and fuel usage

    public double tripcost( double distance) {
        return drive(distance)*fuelPrice;
    }


}