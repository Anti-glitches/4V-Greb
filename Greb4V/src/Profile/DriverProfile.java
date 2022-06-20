package Profile;

public class DriverProfile extends Profile {

    private String status, customerName;
    private double rating; //this is the customer input of the driver rating to be calculated later and it is what being displayed
    private int ratingCount;
    double sumOfAllRating;

    public DriverProfile(String name, int capacity, double initialLatitude, double initialLongitude) {
        super(name, capacity, initialLatitude, initialLongitude);
        status = "Available";
        ratingCount = 1; //this is a counter for the number of time a driver is rated
        customerName = ""; //customer name is initialised as empty string
        sumOfAllRating = 5.0; //the sum of all rating
        rating = round((sumOfAllRating/ratingCount), 1); //we initialise the driver rating to be 5.0
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rate) {
        ratingCount++;
        sumOfAllRating = sumOfAllRating + rate;
        rating = round((sumOfAllRating/ratingCount), 1); 
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    //this is a method to display rating in one decimal place
    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
