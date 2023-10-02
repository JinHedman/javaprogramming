import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;
import java.util.Arrays;
import java.util.TreeSet;

// For sorting and filtering
import java.util.stream.Collectors;

import javax.swing.SpringLayout;

import java.util.Comparator;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;





public class bonuspoang{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of student submissions
        short n = sc.nextShort();
        // Var.s for deadline 
        int d = sc.nextInt();
        short year = (short) (d/10000);
        byte month = (byte) ((d%10000)/100);
        byte day = (byte) (d%100);

        List<Student> students = new ArrayList<Student>();
        
        // Create a list with the object "Students" and fill it with the input
        for(short i = 0; i < n; i++ ){
            Student student = new Student();
            student.setYMD(sc.nextInt());
            student.firstName = sc.next();
            student.lastName = sc.next();
            student.points = sc.nextByte();
            students.add(student);
        }
        sc.close();

        // Filter out students who are not within the time limit
        // If its the same year, then check the month, if its the same month check the day, otherwise filter by checking if the year/month/day is earlier than the deadline
        List<Student> on_time = students.stream().filter(s -> s.getYear() == year ? s.getMonth() == month ? s.getDay() == day ? s.getDay() == day : s.getDay() <= day : s.getMonth() <= month : s.getYear() <= year ).collect(Collectors.toList());
        //System.out.println("ON TIME: "+on_time);

        // Check if the list is empty
        if(on_time.isEmpty()){
            System.out.println("EMPTY");
        }else{

            // Sort by points (we do this comparison because in the case of two students with the same name, we want to keep the one with the most points in the next step, which requires it to be at the top of the duplicates)
            Collections.sort(on_time, Comparator.comparingInt(Student::getPoints).reversed());
            
            // Remove duplicates
            List<Student> no_dupes = on_time.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));

            // Sort by last name, then by first name, we lastly sort the students by date to have the oldest first
            Collections.sort(no_dupes, Comparator.comparing(Student::getlastName).thenComparing(Student::getfirstName).thenComparing(Student::getYear).thenComparing(Student::getMonth).thenComparing(Student::getDay));

            // Print results
            for(Student s: no_dupes){
                System.out.println(s);
            }

        }
    }      
}

// CUSTOM CLASS FOR THE STUDENT
class Student {
    // CLASS ATTRIBUTES
	int date; 
	String firstName; 
    String lastName;
    byte points; 
    short year;
    byte month;
    byte day;

    // DONT NEED A CONSTRUCTOR BECAUSE WE ARE NOT CHAGING THE VALUES OF THE OBJECTS

    // CLASS METHODS
    
    public String toString() {
        return date + " " + firstName+" "+ lastName + " " + points;
    }
    public void setYMD(int date) {
        this.date = date;
        this.year = (short) (date/10000);
        this.month = (byte) ((date%10000)/100);
        this.day = (byte) (date%100);
    }
    public short getYear() {
        return year;
    }public byte getMonth() {
        return month;
    }public byte getDay() {
        return day;
    }
    public String getName() {
        return firstName+" "+ lastName;
    }
    public String getfirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }
    public int getPoints() {
        return points;
    }
    /*
    public String compareTo(Object o) {
        Student comparing_S = (Student) o; 
        return this.lastName.compareTo(comparing_S.lastName) ;
    }
     */
}
