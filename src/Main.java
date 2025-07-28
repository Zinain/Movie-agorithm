package src;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Reader.read("Data\\data.txt");
            Reader.printMoviesArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
