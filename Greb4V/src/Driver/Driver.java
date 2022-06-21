package Driver;

import Profile.DriverProfile;
import java.util.ArrayList;

public class Driver {

    ArrayList<DriverProfile> driver = new ArrayList<DriverProfile>();
    String lastUpdatedTime; 

    //this method is for refering to the driver array inside Driver class
    public ArrayList<DriverProfile> getDriverArray() {
        return driver;
    }

    //this is to add customer to the customer array and set last updated time
    public void add(DriverProfile e, String lastUpdateTime) {
        this.lastUpdatedTime = lastUpdateTime;

        driver.add(e);
    }

    //this is to remove driver from driver array
    public void remove(String driverName, String lastUpdatedTime) {

        if (driver.isEmpty()) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = lastUpdatedTime;

            for (DriverProfile driverProfile : driver) {
                if (driverProfile.getName().equals(driverName) && driverProfile.getStatus().equalsIgnoreCase("Available")) {
                    driver.remove(driverProfile);
                    break;
                }
                else if(driverProfile.getName().equals(driverName) && driverProfile.getStatus().equalsIgnoreCase("Unavailable")){
                    System.out.println("Driver is currently picking up customer. Unable to remove driver");
                    break;
                }
            }
        }
    }

    //this is to find driver by name in the driver array
    public boolean findDriver(String name) {
        for (DriverProfile driverProfile : driver) {
            if (name.equalsIgnoreCase(driverProfile.getName())) {
                return true;
            }
        }
        return false;
    }

    //this is to find driver with "Available" status by name in the driver array
    public boolean findAvailableDriver(String name) {
        for (DriverProfile driverProfile : driver) {
            if (name.equalsIgnoreCase(driverProfile.getName()) && driverProfile.getStatus().equalsIgnoreCase("Available")) {
                return true;
            }
        }
        return false;
    }

    //this is a method to set the status of driver as "Not Available" and to set
    //customer name to DriverProfile
    public void assignCustomer(String driverName, String customerName, String lastUpdatedTime) { //updating location, customer, status
        if (driver.isEmpty()) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = lastUpdatedTime;

            for (DriverProfile driverProfile : driver) {
                if (driverProfile.getName().equals(driverName)) {
                    driverProfile.setStatus("Not Available");
                    driverProfile.setCustomerName(customerName);
                    break;
                }
            }
        }

    }

    //this is a method to set rating inputted by customer into DriverProfile
    public void rating(String driverName, double rating) {
        for (DriverProfile driverProfile : driver) {
            if (findDriver(driverName)) {
                driverProfile.setRating(rating);
                break;
            }
        }
    }

    //this is a method to display driver list
    public void display(String time) {
        System.out.println("Requests List (List Last Updated Time : " + lastUpdatedTime + ")");
        System.out.println("(Current time : " + time + " )");

        System.out.println("===================================================================================================================");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Driver", "Status",
                "Capacity", "Location", "Customer", "Reputation");
        for (DriverProfile driverProfile : driver) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", driverProfile.getName(), driverProfile.getStatus(),
                    driverProfile.getCapacity(), driverProfile.getInitialLatitude() + "," + driverProfile.getInitialLongitude(),
                    driverProfile.getCustomerName(), driverProfile.getRating());
        }

        System.out.println("===================================================================================================================");
    }

}
