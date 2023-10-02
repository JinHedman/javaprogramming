import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;  // Import the Scanner class so user input can be read
import java.util.Collections;
import java.lang.Math;


// Class creation with name "Formelparse"
public class Formelparse {
    //method used to find the end of a pair of parenthesis
    public static int findEndParen(String[] in_p){
        int l_paren = 1;
        int r_paren = 0;
        for(int i = 1; i<=in_p.length;i++){
            //System.out.println(in_p[i]);
            //System.out.println("L_P:" +l_paren);
            //System.out.println("R_P:" +r_paren);
            if(in_p[i].contains("(")){
                l_paren ++;
            }else if(in_p[i].contains(")")){
                r_paren ++;
            }
            if(l_paren == r_paren){
                return i;
            } 

        }
        return 0;
    }
    //main method used to calc inside the parenthesis
    public static Long calc(String[] in_p){
        Long  first = null;
        String op = "";
        Long  second = null;
        for(int i = 0;i<in_p.length;i++){
            //System.out.println(in_p[i]);
            
            //Check if next el. in array is parenthesis
            if(in_p[i].contains("(")){
                int end = findEndParen(Arrays.copyOfRange(in_p, i, in_p.length));
                //System.out.println(Arrays.toString(Arrays.copyOfRange(in_p, i+1, end+i)));

                // if parenthesis then recursively enter calc() method again with new parameters
                if(first == null){
                    first = calc(Arrays.copyOfRange(in_p, i+1, end+i));
                }else{
                    second = calc(Arrays.copyOfRange(in_p, i+1, end+i));
                }
                i += end;
            
            // check which operator to use
            }else if(in_p[i].contains("+")|| in_p[i].contains("-")||in_p[i].contains("*")){
                op = in_p[i];
            // if nothing else the el. must be a int
            }else{
                if(first == null){
                    first = Long.parseLong(in_p[i]);
                }else{
                    second = Long.parseLong(in_p[i]);
                }
            }
        }
        //Switch case for different operations
        switch(op) {
            case "+":
                //System.out.println("RESULTS+:" + (first + second));
                return first + second;
            case "-":
                //System.out.println("RESULTS-:" + (first - second));
                return first - second;
            case "*":
                //System.out.println("RESULTS*:" + first * second);
                return first * second;
          }
          return null;
    }

    //main code that will be executed when file is executed
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        String user_in = input.nextLine();  

        String[] arr_In = user_in.split(" ");

        input.close();

        String[] temp = new String[]{"(", "4", "-", "2", ")", "*", "(", "3", "*", "2", ")"};
        String[] temp2 = new String[]{"(", "3", "*", "(", "(", "4" ,"-", "2" ,")" ,"*" ,"(", "3" ,"*", "2", ")", ")", ")"};

        //System.out.println(findEndParen(temp2));

        System.out.println(calc(arr_In));
    }
}