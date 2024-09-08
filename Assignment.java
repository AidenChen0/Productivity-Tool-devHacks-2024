/*
* This class represents an assignement the user has to comeplete
* An assignment consists of a due date, an ammount of time the user wishes to spend on it in total
* and a unique name.
* You can check how many days you have left to complete an assignment, which checks and compares to the real time date
*/
public class Assignment {
    private String name;
    private SimpleDate dueDate;
    private long totalSeconds;

    Assignment(){
        this.name = "";
        this.dueDate = null;
        this.totalSeconds = 0;
    }
    Assignment(String name, String dueDate, long totalSeconds){
        this.name = name;
        this.dueDate = stringToDate(dueDate);
        this.totalSeconds = totalSeconds;
    }

    //helper method for string to date conversions
    private SimpleDate stringToDate(String dateString){
        return new SimpleDate(Integer.parseInt(dateString.substring(0, 2)), Integer.parseInt(dateString.substring(3, 5)), Integer.parseInt(dateString.substring(6)));
    }

    //getters
    public String getName(){return this.name;}
    public String getDueDate(){return this.dueDate.toString();}
    public long getTotalSeconds(){return this.totalSeconds;}

    //setters
    public void setName(String name){this.name = name;}
    public void setDueDate(String dueDate){this.dueDate = stringToDate(dueDate);}
    public void setTotalSeconds(long totalSeconds){this.totalSeconds = totalSeconds;}

    //checks how many days until the assignment is due
    //output:
    //+: due in this many days
    //0: due today
    //-: due this many days ago
    public int daysUntilDue(){
        return (int)SimpleDate.daysUntil(this.dueDate);
    }

    public String toString(){
        return this.name + "\n" + this.dueDate.toString() + "\n" + this.totalSeconds;
    }
}
