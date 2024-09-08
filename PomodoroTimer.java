import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 * HOW TO USE:
 * Making a new PomodoroTimer starts the clock at 25 mins.
 * Calling startTimer() starts the timer.
 * Calling stopTimer() stops the timer.
 */

public class PomodoroTimer {
    private static final int WORKING_LENGTH = 25; // in minutes
    private static final int BREAK_LENGTH = 5; // in minutes

    private int secondsLeft; // for keeping track of the time elapsedd
    private boolean working; // true when working period is happening, false if rest period is happening
    private int totalSecondsWorked;
    private boolean timerIsRunning;

    private JLabel clock; // the label to update the clock on

    private Timer timer;
    

    /**
     * PARAMETERS:
     *      JLabel clock        A reference to the JLabel that will have the clock.
     */
    public PomodoroTimer(JLabel clock) {
        secondsLeft = WORKING_LENGTH*60;
        working = true;
        totalSecondsWorked = 0;
        timerIsRunning = false;

        this.clock = clock;

        timer = new Timer();
        clock.setText(toMinutesAndSeconds(secondsLeft));
       
    }


    public void startTimer() {
        TimerTask timerTask;

        if (!timerIsRunning && secondsLeft > 0) {
            timer = new Timer();
            timerTask = new TimerTask() {

                @Override
                public void run() {

                    //for testing purposes
                    //System.out.println(toMinutesAndSeconds(secondsLeft));
                    //System.out.println(secondsLeft);

                    clock.setText(toMinutesAndSeconds(secondsLeft));

                    if (working) {
                        if (secondsLeft <= 0) {
                            Notification.notify("Pomodoro Timer", "Stop working! Take a break.");
                            secondsLeft = BREAK_LENGTH*60 + 1;
                            working = false;
                        }
                    } else {
                        if (secondsLeft <= 0) {
                            Notification.notify("Pomodoro Timer", "Break over!");
                            timer.cancel();
                        }
                    }

                    secondsLeft--;
                    totalSecondsWorked++;
                }

                
            };

            timer.scheduleAtFixedRate(timerTask, 0, 1000); // will run the code in run() every second
            timerIsRunning = true;
        } else {
            System.out.println("Timer is already running!"); //for testing purposes
        }
    }

    public void stopTimer() {
        if (timerIsRunning && secondsLeft > 0) {
            timer.cancel();
            timerIsRunning = false;
        } else {
            System.out.println("Timer is already stopped!"); //for testing purposes
        }
    }

    public void reset() {
        secondsLeft = WORKING_LENGTH*60;
        working = true;
        totalSecondsWorked = 0;
        timerIsRunning = false;

        timer = new Timer();
        clock.setText(toMinutesAndSeconds(secondsLeft));
    }

    // Getters & setters
    public int getTotalSecondsWorked() {return totalSecondsWorked;};

    private String toMinutesAndSeconds(int seconds) {
        return seconds/60 + ":" + String.format("%02d", seconds%60);
    }




}
