import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;

import javax.swing.*;

public class MainWindow extends Window{
    private TimeManager mainManager;
    PomodoroTimer pomodoroTimer;
    ReminderTimer reminderTimer;

    public MainWindow(String label){
        super(label);
        mainManager = new TimeManager();

        reminderTimer = new ReminderTimer(1);
        
        // Panel for Give reminders button and open pomodoro button
        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 5));

        // Add a buttons to the panel
        JLabel selectReminderInterval = new JLabel("Reminder Interval:");

        // Open dropdown menu to select time interval
        String[] options = {"1", "5", "10", "15", "20", "30", "60"};
        JComboBox reminderTimeIntervals = new JComboBox(options);
        reminderTimeIntervals.setBackground(getBackground());
        ItemListener selectOptionFunctionality = new ItemListener() {
            // Define functionality here
            @Override
            public void itemStateChanged(ItemEvent e){
                //selectReminderInterval.setText(reminderTimeIntervals.getSelectedItem() + "");
                reminderTimer.resetTimer(Integer.parseInt(reminderTimeIntervals.getSelectedItem() + ""));
            }
        };
        reminderTimeIntervals.addItemListener(selectOptionFunctionality);

        JLabel minutesLabel = new JLabel("mins         ");

        // Open pomodoro timer button
        JButton openPomodoroTimer = new JButton("Open Pomodoro Timer");
        openPomodoroTimer.setBackground(getBackground());
        ActionListener openPomodoroTimerButtonFunctionality = new ActionListener() {
            // Define functionality here
            @Override
            public void actionPerformed(ActionEvent e){
                new PomoTimerGUI("Pomodoro Timer");
            }
        };
        openPomodoroTimer.addActionListener(openPomodoroTimerButtonFunctionality);

        bottomButtons.add(selectReminderInterval);
        bottomButtons.add(reminderTimeIntervals);
        bottomButtons.add(minutesLabel);
        bottomButtons.add(openPomodoroTimer);
        



        JLabel currAssignment = new JLabel("Current Assignment: ");
        currAssignment.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel searchLabel = new JLabel("Search for Assignment Details: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel dividerCross = new JLabel("---------------------------------------------------------------------------------------------------------------------------------");
        JLabel divider = new JLabel("_______________________________________________________________________________________________________");
        JLabel dueDateText = new JLabel("Due Date: ");
        dueDateText.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel timeUntilText = new JLabel("Estimated Time Until Complete: ");
        timeUntilText.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel suggScheduleText = new JLabel("To complete within the due date, you must work a minimum of ___ hours every day.");
        suggScheduleText.setFont(new Font("Arial", Font.PLAIN, 15));


        // Create a new panel for assignment controls, set the layout type
        JPanel assignmentControls = new JPanel();
        assignmentControls.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        assignmentControls.setBackground(getForeground());

        // Add buttons to assignment controls panel
        JButton addAssignment = new JButton("Add Assignment");
        JButton deleteAssignment = new JButton("Delete Assignment");
        addAssignment.setBackground(Color.gray);

        ActionListener addAssignmentFunc = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                addAssignment.setBackground(Color.gray);
                deleteAssignment.setBackground(getForeground());
                makeAddAssignmentWindow();
            }
        };
        addAssignment.addActionListener(addAssignmentFunc);

        ActionListener deleteAssignmentFunc = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                currAssignment.setText("Current Assignment: ");
                deleteAssignment.setBackground(Color.gray);
                addAssignment.setBackground(getForeground());
                makeDeleteAssignmentWindow();
            }
        };
        deleteAssignment.addActionListener(deleteAssignmentFunc);


        JTextField assignmentText = new JTextField(50);


        ActionListener assignmentTextFunc = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                if(mainManager.contains(assignmentText.getText())){
                    Assignment showcase = mainManager.search(assignmentText.getText());
                    currAssignment.setText("Current Assignment: " + showcase.getName());
                    dueDateText.setText("Due Date: " + showcase.getDueDate());
                    timeUntilText.setText("Estimated Time Until Complete: " + ((showcase.getTotalSeconds())/60)/60 + "hours.");
                    suggScheduleText.setText("To complete within the due date, you must work a minimum of " + suggestedSchedule.findSuggestedSchedule(showcase) + " hours every day.");
                }else{
                    currAssignment.setText("Current Assingment Not Found");
                }
            }
        };
        assignmentText.addActionListener(assignmentTextFunc);

        assignmentControls.add(addAssignment);
        assignmentControls.add(deleteAssignment);
        assignmentControls.add(divider);
        assignmentControls.add(searchLabel);
        assignmentControls.add(assignmentText);
        assignmentControls.add(currAssignment);
        assignmentControls.add(dividerCross);
        assignmentControls.add(dueDateText);
        assignmentControls.add(timeUntilText);
        assignmentControls.add(suggScheduleText);


        getWindow().add(assignmentControls, BorderLayout.CENTER);
        getWindow().add(bottomButtons, BorderLayout.SOUTH);

        this.show();
    }

    private void makeAddAssignmentWindow(){
        JFrame addAssignmentFrame = new JFrame();
        addAssignmentFrame.setTitle("Add Assignment");
        addAssignmentFrame.setSize(700, 125);
        addAssignmentFrame.setLocationRelativeTo(null);
        addAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAssignmentFrame.setResizable(false);

        JPanel addAssignmentPanel = new JPanel();
        addAssignmentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addAssignmentPanel.setBackground(Color.CYAN);

        JButton addAssignmentButton = new JButton("Add Assignment");
        addAssignmentPanel.add(addAssignmentButton);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(10);
        JLabel monthLabel = new JLabel("Month Due: ");
        JTextField monthField = new JTextField(10);
        JLabel dayLabel = new JLabel("Day Due: ");
        JTextField dayField = new JTextField(10);
        JLabel yearLabel = new JLabel("Year Due: ");
        JTextField yearField = new JTextField(10);
        JLabel timeLabel = new JLabel("Number Of Expected Work Hours: ");
        JTextField timeField = new JTextField(10);
        addAssignmentPanel.add(nameLabel);
        addAssignmentPanel.add(nameField);
        addAssignmentPanel.add(monthLabel);
        addAssignmentPanel.add(monthField);
        addAssignmentPanel.add(dayLabel);
        addAssignmentPanel.add(dayField);
        addAssignmentPanel.add(yearLabel);
        addAssignmentPanel.add(yearField);
        addAssignmentPanel.add(timeLabel);
        addAssignmentPanel.add(timeField);


        addAssignmentFrame.add(addAssignmentPanel, BorderLayout.CENTER);
        addAssignmentFrame.setVisible(true);

        ActionListener addAssignmentAction = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                String dateString = "";
                int monthNum = Integer.parseInt(monthField.getText());
                if(monthNum <= 9){
                    dateString += "0";
                }
                dateString += monthNum + "/";
                int dayNum = Integer.parseInt(dayField.getText());
                if(dayNum <= 9){
                    dateString += "0";
                }
                dateString += dayNum + "/";
                int yearNum = Integer.parseInt(yearField.getText());
                dateString += yearNum + "";
                String nameInput = nameField.getText();
                int numWorkHours = Integer.parseInt(timeField.getText());
                int numWorkSeconds = numWorkHours * 60 * 60;
                int numWorkMins = numWorkSeconds/60;
                numWorkSeconds %= 60;
                numWorkHours = numWorkMins/60;
                numWorkMins %= 60;
                if(dateString.length() == 10 && nameInput != null){
                    mainManager.addAssignment(nameInput, dateString, numWorkHours, numWorkMins, numWorkSeconds);
                }
                mainManager.closingSave();
                addAssignmentFrame.dispose();
            }
        };
        addAssignmentButton.addActionListener(addAssignmentAction);
    }

    private void makeDeleteAssignmentWindow(){
        JFrame deleteAssignmentFrame = new JFrame();
        deleteAssignmentFrame.setTitle("Delete Assignment");
        deleteAssignmentFrame.setSize(500, 125);
        deleteAssignmentFrame.setLocationRelativeTo(null);
        deleteAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteAssignmentFrame.setResizable(false);

        JPanel deleteAssignmentPanel = new JPanel();
        deleteAssignmentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deleteAssignmentPanel.setBackground(Color.RED);

        JButton deleteAssignmentButton = new JButton("Delete Assignment");
        deleteAssignmentPanel.add(deleteAssignmentButton);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(10);
        deleteAssignmentPanel.add(nameLabel);
        deleteAssignmentPanel.add(nameField);
        
        deleteAssignmentFrame.add(deleteAssignmentPanel, BorderLayout.CENTER);
        deleteAssignmentFrame.setVisible(true);

        ActionListener deleteAssignmentAction = new ActionListener() {
            // Define functionaltiy
            @Override
            public void actionPerformed(ActionEvent e){
                String name = nameField.getText();
                if(name != null){
                    mainManager.assignmentComplete(name);
                    mainManager.closingSave();
                    deleteAssignmentFrame.dispose();
                }
            }
        };
        deleteAssignmentButton.addActionListener(deleteAssignmentAction);
    }
}
