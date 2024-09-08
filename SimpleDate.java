/*
* This class represents a simple date with a month, day and year
* This class can check how many days there are until a specific date
*/

import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SimpleDate{
    private int month;
    private int day;
    private int year;

    SimpleDate(){
        month = 1;
        day = 1;
        year = Calendar.getInstance().get(Calendar.YEAR) + 1;
    }
    SimpleDate(int month, int day, int year){
        if(monthIsValid(month)){
            this.month = month;
        }else{
            this.month = 1;
        }
        if(yearIsValid(year)){
            this.year = year;
        }
        else{
            this.year = Calendar.getInstance().get(Calendar.YEAR) + 1;
        }
        if(dayIsValid(day)){
            this.day = day;
        }else{
            this.day = 1;
        }
    }

    //getters
    public int getMonth(){return this.month;}
    public int getDay(){return this.day;}
    public int getYear(){return this.year;}

    //setters
    public void setMonth(int month){
        if(monthIsValid(month)){
            this.month = month;
        }else{
            this.month = 1;
        }
    }
    public void setDay(int day){
        if(dayIsValid(day)){
            this.day = day;
        }else{
            this.day = 1;
        }
    }
    public void setYear(int year){
        if(yearIsValid(year)){
            this.year = year;
        }
        else{
            this.year = Calendar.getInstance().get(Calendar.YEAR) + 1;
        }
    }

    //toString
    public String toString(){
        String dateString = "";
        if(this.month <= 9){
            dateString += "0";
        }
        dateString += this.month + "/";
        if(this.day <= 9){
            dateString += "0";
        }
        dateString += this.day + "/" + this.year;

        return dateString;
    }
    //toString for the java.time calls
    private String toTimeString(){
        String dateString = "";
        if(this.day <= 9){
            dateString += "0";
        }
        dateString += this.day + " ";
        if(this.month <= 9){
            dateString += "0";
        }
        dateString += this.month + " " + this.year;

        return dateString;
    }
    //valid input checkers
    private boolean monthIsValid(int checkMonth){
        if(checkMonth >= 1 && checkMonth <= 12){
            return true;
        }else{
            return false;
        }
    }
    private boolean dayIsValid(int checkDay){
        if(this.month == 1 ||this.month == 3 ||this.month == 5 ||this.month == 7 ||this.month == 8 || this.month == 10 ||this.month == 12){
            if(checkDay >= 1 && checkDay <= 31){
                return true;
            }else{
                return false;
            }
        }else if(this.month == 4 ||this.month == 6 ||this.month == 9 ||this.month == 11){
            if(checkDay >= 1 && checkDay <= 30){
                return true;
            }else{
                return false;
            }
        }else{
            if(isLeapYear(this.year)){
                if(checkDay >= 1 && checkDay <= 29){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(checkDay >= 1 && checkDay <= 28){
                    return true;
                }else{
                    return false;
                }
            }
        }
        
    }
    
    private boolean yearIsValid(int checkYear){
        if(checkYear >= Calendar.getInstance().get(Calendar.YEAR) && checkYear <= 9999){
            return true;
        }else{
            return false;
        }
    }

    private boolean isLeapYear(int checkYear){
        if(checkYear % 4 == 0){
            if(checkYear % 100 == 0){
                if(checkYear % 400 == 0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        }
        return false;
    }

    //checks how many days there are left unitl "dueDate"
    public static long daysUntil(SimpleDate dueDate){
        long totalDaysUntil = -1;
        SimpleDate currentDate = new SimpleDate( Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.YEAR));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateString1 = currentDate.toTimeString();
        String dateString2 = dueDate.toTimeString();

        try{
            LocalDate date1 = LocalDate.parse(dateString1, dtf);
            LocalDate date2 = LocalDate.parse(dateString2, dtf);
            totalDaysUntil = ChronoUnit.DAYS.between(date1, date2);
        }catch(Exception e){
            System.out.println("tuff");
        }

        return totalDaysUntil;
    }

}
