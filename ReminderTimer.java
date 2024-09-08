// known bug: will 


import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class ReminderTimer {
    private int interval; // in mins
    private Timer timer;
    private TimerTask timerTask;

    public ReminderTimer(int interval) {
        //System.out.println("constructor");
        this.interval = interval;
        timer = new Timer();

       

        setTimer();
    }

    public void resetTimer(int newInterval) {
        interval = newInterval;
        timer.cancel(); //System.out.println("timer cancelled");
        timer = new Timer();

        

        //System.out.println("settimer called by resettimer");
        setTimer();
    }

    private void setTimer() {
        if (interval > 0) {
            //System.out.println(interval);

            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Notification.notify("Assignment Manager", Threats.getThreat());
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, interval*1000*60); //runs every interval*minutes
            //System.out.println(interval + "e");
        }
    }

}
