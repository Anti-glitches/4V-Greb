package Driver;

import Profile.DriverProfile;
import java.util.ArrayList;

public class Driver {

    ArrayList<DriverProfile> driver = new ArrayList<DriverProfile>();
    String lastUpdatedTime;

    //experimental
    public ArrayList<DriverProfile> referDriver() {
        return driver;
    }
    //until here

    public void add(DriverProfile e, String lastUpdateTime) {
        this.lastUpdatedTime = lastUpdateTime;

        driver.add(e);
    }

    public void remove(String name, String lastUpdatedTime) {

        if (driver.size() == 0) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = lastUpdatedTime;

            for (DriverProfile driverProfile : driver) {
                if (findDriver(name)) {
                    driver.remove(name);
                    break;
                }
            }
        }
    }

    public boolean findDriver(String name) {
        for (DriverProfile driverProfile : driver) {
            if (name.equalsIgnoreCase(driverProfile.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean findAvailableDriver(String name) {
        for (DriverProfile driverProfile : driver) {
            if (name.equalsIgnoreCase(driverProfile.getName()) && driverProfile.getStatus().equalsIgnoreCase("Available")) {
                return true;
            }
        }
        return false;
    }

    public void assignCustomer(String driverName, String customerName, String lastUpdatedTime) { //updating location, customer, status
        if (driver.size() == 0) {
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

    public void rating(String driverName, double rating) {
        for (DriverProfile driverProfile : driver) {
            if (findDriver(driverName)) {
                driverProfile.setRating(rating);
                break;
            }
        }
    }
    
    public ArrayList<DriverProfile> getDriverArr(){
        return driver;
    }

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
