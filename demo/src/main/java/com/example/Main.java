package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Needed for Hash map of genres to work inits the map
        Calculator calc = new Calculator();
        
        //Reads the data
        try {
            Reader.read("demo\\src\\main\\resources\\data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Reads user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a movie you like: ");
        String userMovieName = sc.nextLine().trim();
        //If movie is not found in the data set
        Movie userMovie = Reader.findMovie(userMovieName);
        if (userMovie == null) {
            System.out.println("Movie not found.");
            sc.close();
            return;
        }
        //If found, recommends 3 similar movies
        List<Movie> recommendations = Reader.recommend(userMovie, 3);
        System.out.println("You might also like:");
        for (Movie m : recommendations) {
            System.out.println(m.name);
        }
        sc.close();
    }
}
