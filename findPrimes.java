import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;  // Import the Scanner class so user input can be read
import java.util.Collections;
import java.lang.Math;


public class findPrimes {
    //main code that will be executed when file is executed
    static void findPrime(){
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        long n = input.nextInt();  

        long sum = 0;

        for(long i = 0; i<n; i++){
            sum += input.nextLong();
        }

        input.close();
        
        long digsum = String.valueOf(sum)
            .chars()
            .map(Character::getNumericValue)
            .sum();


        if(sum%2 == 0){
            System.out.println(2);
        }else if(digsum%3 == 0){
            System.out.println(3);
        }
        else{

            for(long i = 3; i<=sum; i+=2){

                if(sum%i == 0){
                    System.out.println(i);

                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        findPrime();
    }
}