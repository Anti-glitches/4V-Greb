package Utils;

import java.util.Date;

public class Time {
    private final long startTime;
    private long stopTime;

    // this is where we initialized and get the initial value of time from the computer
    public Time() {
        Date d = new Date();
        
        startTime = d.getTime();
    }

    // this is where we get the recentest time so that we can
    // calculate the difference form the initial set time/startTime
    // in the constructor
    public String time() {
        Date d = new Date();
        
        stopTime = d.getTime();

        return calcTime(startTime, stopTime);
    }

    // Calculate time based on driverToCustomerTime and customerToDestinationTime
    // Convert the time to String for display purposes
    public String calcTime(long t1, long t2) {
        int time = (int) (t2 - t1);
        int timeInSec = (time / 1000); //this is an actual time in second, however it could be interpreted as minute as we assume min = sec and hour = min

        int hh = (timeInSec / 60) % 24; //24 is because we don't want to get it over; So it'll start from 00 again 
        int mm = (timeInSec % 60);      

        String displayHH = String.valueOf(hh);
        String displayMM = String.valueOf(mm);

        if (hh < 10) {
            displayHH = "0" + displayHH;
        }

        if (mm < 10) {
            displayMM = "0" + displayMM;
        }

        return displayHH + displayMM;
    }
}
