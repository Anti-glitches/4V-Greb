package Profile;

import Utils.Calculation;
import java.util.ArrayList;

public class CustomerProfile extends Profile {

    Calculation calc = new Calculation();
    private double finalLatitude, finalLongitude;
    private String chosenEAT;
    private String status;
    private boolean hasRated;
    private String chosenDriver; //this is where the chosen driver resides for rating
    private int customerToDestinationTime; //this is where we store the time from customer to destination
    private ArrayList<DriverProfile> driver; //this is all the driver array
    private ArrayList<String> driverName = new ArrayList<>(); //this is for time calc && you can just driver.get(i).getName()
    private ArrayList<Integer> driverToCustomerTime = new ArrayList<>(); //this is where we store the time from driver to customer
    private ArrayList<String> allPossibleEAT = new ArrayList<>(); //this is the list of all the EAT for all drivers so the customer can choose
    
    //this is the constructor
    public CustomerProfile(String name, String EAT, int capacity, double initialLatitude, double initialLongitude, double finalLatitude, double finalLongitude) {
        super(name, capacity, initialLatitude, initialLongitude);
        this.finalLatitude = finalLatitude;
        this.finalLongitude = finalLongitude;
        this.chosenEAT = EAT;
        this.status = "Pending";
        this.chosenDriver = "";
    }
    
    public String getChosenDriver() {
        return chosenDriver;
    }

    public void setChosenDriver(String chosenDriver) {
        this.chosenDriver = chosenDriver;
    }

    public double getFinalLatitude() {
        return finalLatitude;
    }

    public double getFinalLongitude() {
        return finalLongitude;
    }

    public String getChosenEAT() {
        return chosenEAT;
    }

    public String getStatus() {
        return status;
    }

    public void setChosenEAT(String chosenEAT) {
        this.chosenEAT = chosenEAT;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //this is where we set an array of driver from the Driver class
    //so that we have access to be able to calculate the EAT for
    //the driver to the customer
    public void setDriver(ArrayList<DriverProfile> driver) {
        this.driver = driver;
    }

    //this is a method to calculate the time from customer to destination
    public void customerToDestination() { 
        Calculation calc = new Calculation();

        double dis = calc.distance(getInitialLatitude(),
                getInitialLongitude(), getFinalLatitude(), getFinalLongitude());

        this.customerToDestinationTime = calc.getTimeTaken(dis);
    }

    //not sure
    public void driverToCustomer() {
        for (DriverProfile driverProfile : driver) {
            Integer time = calc.getTimeTaken(calc.distance(driverProfile.getInitialLatitude(),
                    driverProfile.getInitialLongitude(), getInitialLatitude(), getInitialLongitude()));
            driverName.add(driverProfile.getName());
            driverToCustomerTime.add(time);
        }

    }

    //this is where we add up all the driver time from driver to customer and
    //customer to destination and put it inside an array
    public void setAllPossibleEAT(String currentTime) {
        for (int i = 0; i < driverToCustomerTime.size(); i++) {
            allPossibleEAT.add(calc.currentTimeEAT((this.customerToDestinationTime + this.driverToCustomerTime.get(i)), currentTime));
        }
    }

    //this is where we get driver EAT based on their name
    public String getDriverEATBasedOnName(String driverName) {
        for (int i = 0; i < driver.size(); i++) {
            if (driver.get(i).getName().equals(driverName)) {
                return allPossibleEAT.get(i);
            }
        }
        return "**************\nDriver is not found.\n**************";
    }

    //this is a method to get a specific EAT to be displayed in the Customer class
    public String getDriverEATBasedOnIndex(int index) {       
        return allPossibleEAT.get(index);
    }

    public boolean isHasRated() {
        return hasRated;
    }

    public void setHasRated(boolean hasRated) {
        this.hasRated = hasRated;
    }

    
    
}
