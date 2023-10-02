import java.util.Scanner;

public class Uniqword{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // value of N
        int N = scanner.nextInt();

        scanner.nextLine();
        // read input of sequence of characters
        String input = scanner.nextLine();

        // variable for starting position first unique subsequence of length N
        int uniqPos = -1;

        // iterate through each possible subsequence of length N in the input
        for (int i = 0; i <= input.length() - N; i++) {
            //System.out.println("NOW CHECKING: "+input.substring(i, i + N));
            String subsequence = input.substring(i, i + N);

            // check if subsequence is unique
            boolean unique = true;
            for (int j = 0; j < input.length() - N+1; j++) {
                //System.out.println(input.substring(j, j + N));
                if (i != j && subsequence.equals(input.substring(j, j + N))) {
                    unique = false;
                    break;
                }
            }

            // if subsequence is unique, update the uniqPos var to the current position (i) and break
            if (unique) {
                uniqPos = i;
                break;
            }
        }

        // print uniqPos
        System.out.println(uniqPos);
    }
}