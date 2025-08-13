package com.example;

public class Movie {
    String name;
    int genreNum;
    double rating;
    double budget;

    Movie(String name, int genreNum, double rating, double budget) {
        this.name = name;
        this.genreNum = genreNum;
        this.rating = rating;
        this.budget = budget;
    }

    double[] toVector() {
        return new double[]{genreNum, rating, budget};
    }
}
