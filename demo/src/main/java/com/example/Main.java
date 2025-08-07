package com.example;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        try {
            calc.trainModel();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*try {
            Reader.read("Data\\data.txt");
            Reader.printMoviesArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }*/
    }
}
