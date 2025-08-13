package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {

    static List<Movie> movies = new ArrayList<>();

    /*Reads and splits words into a 2d array of lines and words */
    public static void read(String filePath) throws IOException{

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for(int i = 0; i < lines.size(); i++){

            String line = lines.get(i).trim();

            movies.add(Calculator.calculate(line));
            
        }
    }

    static Movie findMovie(String name) {
        for (Movie m : movies) {
            if (m.name.equalsIgnoreCase(name)) return m;
        }
        // If not found, try fetching from API
        Movie fetched = Calculator.fetchMovie(name);
        if (fetched != null) {
            movies.add(fetched);
            return fetched;
        }
    return null;
    }

    static List<Movie> recommend(Movie baseMovie, int k) {
        double[] baseVec = baseMovie.toVector();
        return movies.stream()
                .filter(m -> !m.name.equalsIgnoreCase(baseMovie.name))
                .sorted(Comparator.comparingDouble(m -> Calculator.distance(baseVec, m.toVector())))
                .limit(k)
                .collect(Collectors.toList());
    }
    
    /*Prints the moviesArray for debugging purposes */
    public static void printMoviesArray() {
        for (Movie m : movies){
            System.out.println(m.name + " " + m.genreNum + " " + m.budget);
        }
    }
}
