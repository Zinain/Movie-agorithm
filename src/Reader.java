package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    public static String [][] moviesArray;

    /*Reads and splits words into a 2d array of lines and words */
    public static void read(String filePath) throws IOException{

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        moviesArray = new String [lines.size()][];

        for(int i = 0; i < lines.size(); i++){

            String line = lines.get(i).trim();
            moviesArray[i] = line.split(",");
            
        }
    }
    
    /*Prints the moviesArray for debugging purposes */
    public static void printMoviesArray() {
        for (int i = 0; i < moviesArray.length; i++) {
            System.out.print("Line " + (i + 1) + ": ");
            for (String word : moviesArray[i]) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
