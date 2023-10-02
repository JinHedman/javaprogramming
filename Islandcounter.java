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


// KATTTIS
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;



public class Islandcounter{
    public static void main(String[] args) {
        //Scanner object
        Kattio io = new Kattio(System.in, System.out);
        // dimensions of the island
        int x = io.getInt();
        io.getInt();

        String first = io.getWord();
        int y = first.length();


        // array for the island
        char[][] i_array = new char[x][y];
        boolean[][] visited = new boolean[x][y];

        // DO WE NEED TO CONVERT IT INTO AN ARRAY?
        // input for the island, read a whole row and splits them into columns
        i_array[0] = first.toCharArray();

        for (int i = 1; i < x; i++) {
            String line = io.getWord();
            i_array[i] = line.toCharArray();
            
        }

        /*
        // USED TO TIME READING OF FILE AND ALGORITHM
        try {
            long startTime = System.nanoTime();
            File myObj = new File("C:/Users/filip/Desktop/java/Islandcounter5.0/islands.0.in");
            Scanner myReader = new Scanner(myObj);
            int x = myReader.nextInt();
            int y = myReader.nextInt();
            char[][] i_array = new char[x][y];
            boolean[][] visited = new boolean[x][y];
            myReader.nextLine();
            for(int i = 0; i < x; i++){
                String line = myReader.nextLine();
                i_array[i] = line.toCharArray();
            }
            myReader.close();
            long endTime = System.nanoTime();
            System.out.println("Time to read: " + (endTime-startTime)*Math.pow(10,-9) + "s");

            long startTime2 = System.nanoTime();
            System.out.println(countIslands(i_array, visited));
            long endTime2 = System.nanoTime();
            System.out.println("Time to count: " + (endTime2-startTime2)*Math.pow(10,-9) + "s");


            //System.out.println(Arrays.deepToString(i_array).replace("], ", "]\n"));

        }catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        */

          


        //System.out.println(Arrays.deepToString(visited));
        //System.out.println(Arrays.deepToString(i_array).replace("], ", "]\n"));
        System.out.println(countIslands(i_array, visited));
        io.close();

    }
    public static int countIslands(char[][] i_array, boolean[][] visited) {
        // UP, DOWN, LEFT, RIGHT
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Queue for which island element to check its neighbours next 
        Queue<int[]> queue = new LinkedList<>();

        // Island count
        int count = 0;
        //ROWS
        for (int i = 0; i < i_array.length; i++) {
            //COLUMNS
            for (int j = 0; j < i_array[i].length; j++) {
                // check if the current element has been visited
                if (!visited[i][j] ) {
                    visited[i][j] = true;
                    // check if the current element is an island
                    if(i_array[i][j] == '@'){
                        // increase the island count
                        count ++;
                        // add the current element to the queue
                        queue.add(new int[]{i, j});
                        //System.out.println("Found an island at " + i + " " + j);

                        // While loop for checking the adjecent elements of the current element
                        while(!queue.isEmpty()){
                            // Remove the earliest added element from the queue
                            int[] current = queue.remove();

                            // Check the adjecent elements of the current element
                            for(int[] direction2 : directions){
                                int x = current[0] + direction2[0];
                                int y = current[1] + direction2[1];
                                // check if the element is inside bounds and if it hasnt been visited yet
                                if ( x >= 0 && x < i_array.length && y >= 0 && y < i_array[i].length && !visited[x][y] ) {
                                    visited[x][y] = true;

                                    //System.out.println("Found neighbour at: " + x + " " + y);
                                    // add the adjecent element to the queue if it is "@"

                                    //then if adjecent element is "@" 
                                    if(i_array[x][y] == '@'){
                                        queue.add(new int[]{x, y});

                                    }
                                    //System.out.println("Currently at: " + Arrays.deepToString(queue.toArray()));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return count;
    }        
}



class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}