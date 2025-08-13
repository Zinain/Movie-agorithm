package com.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Calculator {

    public static HashMap<String, Integer> genreMap = new HashMap<>();
    static final String API_KEY = "d00d2078";

    private J48 tree;

    public void trainModel() throws Exception {
        DataSource source = new DataSource("movies.csv");
        Instances data = source.getDataSet();

        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1); // last column is class

        tree = new J48(); // Decision Tree
        tree.buildClassifier(data);

        System.out.println(tree); // Print the tree
    }

    public Calculator(){
        genreMap.put("Horror", 0);
        genreMap.put("Thriller", 1);
        genreMap.put("Mystery", 2);
        genreMap.put("Crime", 3);
        genreMap.put("Drama", 4);
        genreMap.put("Biography", 5);
        genreMap.put("History", 6);
        genreMap.put("War", 7);
        genreMap.put("Western", 8);
        genreMap.put("Adventure", 9);
        genreMap.put("Action", 10);
        genreMap.put("Superhero", 11);
        genreMap.put("SciFi", 12);
        genreMap.put("Fantasy", 13);
        genreMap.put("Animation", 14);
        genreMap.put("Family", 15);
        genreMap.put("Comedy", 16);
        genreMap.put("Musical", 17);
        genreMap.put("Romance", 18);
        genreMap.put("Sport", 19);
        genreMap.put("Documentary", 20);
        genreMap.put("Experimental", 21);
        genreMap.put("Short", 22);
        genreMap.put("Music", 23);
        genreMap.put("FilmNoir", 24);
        genreMap.put("GameShow", 25);
        genreMap.put("Reality", 26);
        genreMap.put("Talk", 27);
        genreMap.put("News", 28);
        genreMap.put("Adult", 29);

    }

    /*Calculates the total points for the suggestion */
    public static Movie calculate(String line){
        String[] parts = line.split(",\\s*");
        String name = parts[0];
        String genre = parts[1];
        double rating = Double.parseDouble(parts[2]);
        double budget = Double.parseDouble(parts[3].replace("$", "").replace("M", ""));
        Integer genreNum = genreMap.get(genre);
        if (genreNum != null) {
            return new Movie(name, genreNum, rating, budget);
        }
        return null;
    }

    static double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static Movie fetchMovie(String title) {
        try {
            String urlStr = "http://www.omdbapi.com/?t=" + URLEncoder.encode(title, "UTF-8") + "&apikey=" + API_KEY;
            URL url = new URL(urlStr);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder json = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) json.append(inputLine);
            in.close();

            JSONObject obj = new JSONObject(json.toString());
            if (!obj.getString("Response").equals("True")) {
                System.out.println("Movie not found online.");
                return null;
            }

            String name = obj.getString("Title");
            String genreStr = obj.getString("Genre").split(",")[0].trim();
            double rating = obj.has("imdbRating") ? Double.parseDouble(obj.getString("imdbRating")) : 0.0;

            // OMDb often lacks budget; this is just an example
            double budget = 0.1; // fallback
            if (obj.has("BoxOffice") && !obj.getString("BoxOffice").equalsIgnoreCase("N/A")) {
                String box = obj.getString("BoxOffice").replace("$", "").replace(",", "");
                try {
                    budget = Double.parseDouble(box) / 1_000_000.0;
                } catch (NumberFormatException e) {}
            }

            Integer genreNum = genreMap.get(genreStr);
            if (genreNum == null) {
                System.out.println("Genre not recognized: " + genreStr);
                return null;
            }

            Movie m = new Movie(name, genreNum, rating, budget);
            appendToDataFile(name, genreStr, rating, budget);
            return m;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void appendToDataFile(String name, String genre, double rating, double budget) {
        try (FileWriter fw = new FileWriter("demo\\src\\main\\resources\\data.txt", true)) {
            fw.write(String.format("\n%s, %s, %.1f, $%.1fM", name, genre, rating, budget));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
