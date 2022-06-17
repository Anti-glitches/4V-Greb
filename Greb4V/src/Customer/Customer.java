package Customer;

import Profile.CustomerProfile;
import Profile.DriverProfile;
import java.util.ArrayList;

public class Customer {

    ArrayList<CustomerProfile> customer = new ArrayList<CustomerProfile>();
    ArrayList<DriverProfile> driver;
    String lastUpdatedTime;

    //this is to set the driver array into the CustomerProfile
    //this is also to set the EAT, customerToDestination, driverToCustomer inside the CustomerProfile
    public void setDriverProfile(String customerName, ArrayList<DriverProfile> driver, String currentTime) {
        if (customer.isEmpty()) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = currentTime;

            for (CustomerProfile customerProfile : customer) {
                if (customerProfile.getName().equals(customerName)) {
                    this.driver = driver;
                    customerProfile.setDriver(driver); //this is a method to store driver array in CustomerProfile
                    customerProfile.customerToDestination();
                    customerProfile.driverToCustomer();
                    customerProfile.setAllPossibleEAT(currentTime);
                }
            }
        }
    }

    public ArrayList<CustomerProfile> getCustomerArray() {
        return customer;
    }

    //this is to add customer to the customer array and set last updated time
    public void add(CustomerProfile e, String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;

        customer.add(e);
    }

    //this is to set status of the customer
    public void status(String name, String lastUpdatedTime, String status) {
        if (customer.isEmpty()) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = lastUpdatedTime;

            for (CustomerProfile customerProfile : customer) {
                if (customerProfile.getName().equalsIgnoreCase(name)) {
                    customerProfile.setStatus(status);
                    break;
                }
            }
        }
    }

    //this is to remove customer from customer array
    public void remove(String name, String lastUpdatedTime) {
        if (customer.isEmpty()) {
            System.out.println("List is empty");
        } else {
            this.lastUpdatedTime = lastUpdatedTime;

            for (CustomerProfile customerProfile : customer) {
                int i = 0;
                if (customerProfile.getName().equalsIgnoreCase(name)) {
                    customer.remove(i);
                    break;
                }
                i++;
            }
        }
    }

    //this is to find customer by name in the customer array
    public boolean findCustomer(String name) {
        for (CustomerProfile customerProfile : customer) {
            if (name.equalsIgnoreCase(customerProfile.getName())) {
                return true;
            }
        }
        return false;
    }

    //this is to set the final latitude based on existing customer name inside the array
    public double setFinalLatitude(String customerName) {
        for (CustomerProfile customerProfile : customer) {
            if (findCustomer(customerName)) {
                return customerProfile.getFinalLatitude();
            }
        }
        return 0.0;
    }

    //this is to set the final longitude based on existing customer name inside the array
    public double setFinalLongitude(String customerName) {
        for (CustomerProfile customerProfile : customer) {
            if (findCustomer(customerName)) {
                return customerProfile.getFinalLongitude();
            }
        }
        return 0.0;
    }

    //this is to get the capacity based on existing customer name inside the array
    public int getCapacity(String name) {
        for (CustomerProfile customerProfile : customer) {
            if (findCustomer(name)) {
                return customerProfile.getCapacity();
            }
        }
        return -1;
    }

    //this is a method to set chosen EAT inside CustomerProfile
    //and the reason for the two loops is to check whether or not the customer
    //and the driver existed
    public void asssignDriverTImeToEAT(String customerName, String driverName) {
        for (CustomerProfile customerProfile : customer) {
            if (customerName.equals(customerProfile.getName())) {
                for (DriverProfile driverProfile : driver) {
                    if (driverProfile.getName().equalsIgnoreCase(driverName)) {
                        customerProfile.setChosenEAT(customerProfile.getDriverEATBasedOnName(driverName));
                    }
                }
            }
        }
    }

    //this method is to get the potential EAT so that it can be displayed in
    //the displayRatingDriver so that customer can choose which driver EAT
    //that they prefer
    public String getPotentialEAT(String customerName, int index) {
        for (CustomerProfile customerProfile : customer) {
            if (customerProfile.getName().equals(customerName)) {
                return customerProfile.getDriverEATBasedOnIndex(index);
            }
        }
        return "Error";
    }

    //this is a method to set the driver chosen by customer into CustomerProfile
    public void setCustomerChosenDriver(String customerName, String driverName) {
        for (CustomerProfile customerProfile : customer) {
            if (customerProfile.getName().equals(customerName)) {
                customerProfile.setChosenDriver(driverName);
                break;
            }
        }

    }

    //this is a method to display customers list
    public void display(String time) {
        System.out.println("Requests List (List Last Updated Time : " + lastUpdatedTime + ")");
        System.out.println("(Current time : " + time + " )");

        System.out.println("==================================================================================================================================");
        System.out.printf("%-20s %-20s %-25s %-20s %-20s %-20s\n", "Customer", "Status", "Expected Arrival Time",
                "Capacity", "Starting Point", "Destination");
        for (CustomerProfile customerProfile : customer) {
            System.out.printf("%-20s %-20s %-25s %-20s %-20s %-20s\n", customerProfile.getName(), customerProfile.getStatus(),
                    customerProfile.getChosenEAT(), customerProfile.getCapacity(), customerProfile.getInitialLatitude() + ","
                    + customerProfile.getInitialLongitude(), customerProfile.getFinalLatitude() + "," + customerProfile.getFinalLongitude());
        }

        System.out.println("==================================================================================================================================");
    }

    //this is a method to display drivers list for customer to choose driver from
    public void displayRatingDriver(int cap, String time, String customerName) {
        System.out.println("Requests List (List Last Updated Time : " + lastUpdatedTime + ")");
        System.out.println("(Current time : " + time + " )");

        System.out.println("=============================================================================================================================");
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Driver", "Capacity",
                "EAT", "Reputation");

        for (int i = 0; i < driver.size(); i++) {
            if (cap <= driver.get(i).getCapacity() && driver.get(i).getStatus().equalsIgnoreCase("Available")) {
                System.out.printf("%-20s %-20s %-20s %-20s\n", driver.get(i).getName(), driver.get(i).getCapacity(),
                        getPotentialEAT(customerName, i),
                        driver.get(i).getRating());
            }
        }

        System.out.println("=============================================================================================================================");
    }
}
