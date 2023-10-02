import java.util.Scanner;
import java.util.Arrays;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.Math;





public class Word{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder myString = new StringBuilder();

        sc.nextLine();
        for(int i = 0; i < n; i++) {
            myString.append(sc.next());
        }

        System.out.println(myString.toString());

        sc.close();
    }        
}
