package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Reader {

    public static HashMap<String, Double> calcMap = new HashMap<>();

    /*Reads and splits words into a 2d array of lines and words */
    public static void read(String filePath) throws IOException{

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for(int i = 0; i < lines.size(); i++){

            String line = lines.get(i).trim();

            calcMap.put(line.split(",")[0], Calculator.calculate(line.split(",")[1],line.split(",")[2],line.split(",")[3]));
            
        }
    }
    
    /*Prints the moviesArray for debugging purposes */
    public static void printMoviesArray() {
        for (String name : calcMap.keySet()){
            System.out.println(name + " - " + calcMap.get(name));
        }
    }
}
