import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;


// We want PomoTimer to extend Window
class PomoTimerGUI extends Window {
    private PomodoroTimer pomodoroTimer;
    
    public PomoTimerGUI(String label){
        super(label);
        
        // JLabel for the clock
        JLabel clock = new JLabel();
        clock.setBounds(0, 100, 300, 100);
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        clock.setFont(new Font("SansSerif", Font.PLAIN, 20));

        pomodoroTimer = new PomodoroTimer(clock);
        getWindow().add(clock);



        getWindow().setSize(500, 300);
        getWindow().setResizable(false);


        // Create a new panel for pomodoro controls, set the layout type
        JPanel pomoControls = new JPanel();
        pomoControls.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

         // Add a buttons to the panel
        // Start pomoodoro button
        JButton startPomo = new JButton("Start Pomodoro");
        startPomo.setBackground(getBackground());
        ActionListener startPomoFunc = new ActionListener() {
            // Define functionality here
            @Override
            public void actionPerformed(ActionEvent e){
                //System.out.println("Test functionality");
                pomodoroTimer.startTimer();
            }
        };
        startPomo.addActionListener(startPomoFunc);

        // Pause pomodoro button
        JButton pausePomo = new JButton("Pause Pomodoro");
        pausePomo.setBackground(getBackground());
        ActionListener pausePomoFunc = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                //System.out.println("Test func 2");
                pomodoroTimer.stopTimer();
            }
        };
        pausePomo.addActionListener(pausePomoFunc);

        // Reset pomodoro button
        JButton resetPomo = new JButton("Reset Pomodoro");
        resetPomo.setBackground(getBackground());
        ActionListener resetPomoFunc = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                //System.out.println("Test func 2");
                pomodoroTimer.reset();
            }
        };
        resetPomo.addActionListener(resetPomoFunc);

        pomoControls.add(startPomo);
        pomoControls.add(pausePomo);
        pomoControls.add(resetPomo);

        getWindow().add(pomoControls, BorderLayout.SOUTH);

        this.show();
    }


}
