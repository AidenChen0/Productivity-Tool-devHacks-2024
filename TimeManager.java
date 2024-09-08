/**
 * FORMAT OF saveData.txt:
 * 1st line: Total number of assignments
 * Each group of 3 lines after that:
 * 		1: Assignment name
 * 		2: Due date (month/day/year)
 * 		3: Total hours it will take to complete
 * 
 * Ex:
 * 2
 * my first assignment
 * 02/12/2024
 * 20
 * assignment #2
 * 04/02/2025
 * 50
 */



import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.io.File;
import java.util.Scanner;
import java.util.TimerTask;
import java.io.FileWriter;
import java.sql.Time;
import java.util.Timer;


class TimeManager {
	private static final int MAX_ASSIGNMENTS = 100;
	private Assignment[] assignments;
	private int numAssignments;

	TimeManager(){
		assignments = new Assignment[MAX_ASSIGNMENTS];

		File saveData = new File("saveData.txt");
		try{
			if(saveData.createNewFile()){
				numAssignments = 0;
			}else{
				try{
					Scanner input = new Scanner(saveData);
					while(input.hasNext()){
						this.numAssignments = Integer.parseInt(input.nextLine());
						for(int i = 0; i < this.numAssignments; i++){
							assignments[i] = new Assignment(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()));
						}
					}
					input.close();
				}catch(Exception excptn){
				}
			}
		}catch(Exception e){
		}

		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Notification.notify("slay", Threats.getThreat());
			}
		};

		//timer.scheduleAtFixedRate(timerTask, 0, 1000); // will run the code in run() every second
	}
	
	public void open() {
		JFrame frame = new JFrame("slss");
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	public void addAssignment(String name, String date, int hours, int minuites, int seconds){
		boolean isDupe = false;
		for(int i = 0; i < this.numAssignments; i++){
			if(assignments[i].getName().equals(name)){
				isDupe = true;
			}
		}
		if(!isDupe){
			long totalSeconds = (long)(hours * 60 * 60) + (long)(minuites * 60) + (long)seconds;
			assignments[numAssignments] = new Assignment(name, date, totalSeconds);
			numAssignments++;
		}
	}
	
	public void assignmentComplete(String name){
		boolean removed = false;
		int indexRemoved = -1;

		for(int i = 0; i < this.numAssignments && !removed; i++){
			if(assignments[i].getName().equals(name)){
				assignments[i] = null;
				this.numAssignments--;
				indexRemoved = i;
			}
		}

		if(removed){
			for(int j = indexRemoved; j < this.numAssignments; j++){
				assignments[j] = assignments[j + 1];
				if(j + 1 == this.numAssignments){
					assignments[j + 1] = null;
				}
			}
		}
	}
	
	public void closingSave(){
		try{
			File writeFile = new File("saveData.txt");
			FileWriter dataWriter = new FileWriter(writeFile);
			dataWriter.write(this.numAssignments + "\n");
			for(int i = 0; i < this.numAssignments; i++){
				dataWriter.write(this.assignments[i].getName() + "\n");
				dataWriter.write(this.assignments[i].getDueDate() + "\n");
				dataWriter.write(this.assignments[i].getTotalSeconds() + "\n");
			}
			dataWriter.close();
		}catch(Exception e){
		}
	}

	public boolean contains(String name){
		for(int i = 0; i < this.numAssignments; i++){
			if(assignments[i].getName().equals(name)){
				return true;
			}
		}
		return false;
	}

	public Assignment search(String name){
		for(int i = 0; i < this.numAssignments; i++){
			if(assignments[i].getName().equals(name)){
				return assignments[i];
			}
		}
		return null;
	}
}
