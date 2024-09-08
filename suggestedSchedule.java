import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class suggestedSchedule {
    public static final double HOUR_MIN = 60; //Constant to convert between mins and hours

    public static double findSuggestedSchedule(Assignment assignment) {
        double daysUntilDue = assignment.daysUntilDue();
        double expectedTime = ((double)assignment.getTotalSeconds())/60;
        expectedTime = calculateTime(daysUntilDue, expectedTime); //Returns suggested min per day until due
        return expectedTime;
    }
    
    public static double calculateTime(double daysUntilDue, double expectedTime) {
        double suggestedMin;
        double minimumMins = expectedTime/daysUntilDue;

        suggestedMin = convertToHours(minimumMins);
        return suggestedMin;
    }

    public static double convertToHours(double timeMins) {
        double expectedHours = timeMins/HOUR_MIN;

        BigDecimal bd = new BigDecimal(Double.toString(expectedHours));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void scheduleWork(double expectedTime) {
        try {
            File writeFile = new File("suggestedSchedule.txt");
            FileWriter dataWriter = new FileWriter(writeFile);
            dataWriter.write(expectedTime + "");

            dataWriter.close();
        } catch (Exception e) {
        }
    }
}
