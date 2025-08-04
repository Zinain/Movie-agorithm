package src;

import java.util.HashMap;

public class Calculator {

    public static HashMap<String, Double> genreMap = new HashMap<>();

    public Calculator(){
        genreMap.put("Horror", (double) 0);
        genreMap.put("Action", (double) 1);
        genreMap.put("Adventure", (double) 2);
        genreMap.put("Animation", (double) 3);
        genreMap.put("Biography", (double) 4);
        genreMap.put("Comedy", (double) 5);
        genreMap.put("Crime", (double) 6);
        genreMap.put("Drama", (double) 7);
        genreMap.put("Fantasy", (double) 8);
        genreMap.put("History", (double) 9);
        genreMap.put("Musical", (double) 10);
        genreMap.put("Mystery", (double) 11);
        genreMap.put("Romance", (double) 12);
        genreMap.put("SciFi", (double) 13);
        genreMap.put("Thriller", (double) 14);
        genreMap.put("War", (double) 15);
        genreMap.put("Western", (double) 16);
        genreMap.put("Documentary", (double) 17);
        genreMap.put("Family", (double) 18);
        genreMap.put("Sport", (double) 19);
        genreMap.put("Short", (double) 20);
        genreMap.put("Music", (double) 21);
        genreMap.put("News", (double) 22);
        genreMap.put("Reality", (double) 23);
        genreMap.put("Talk", (double) 24);
        genreMap.put("FilmNoir", (double) 25);
        genreMap.put("GameShow", (double) 26);
        genreMap.put("Adult", (double) 27);
        genreMap.put("Experimental", (double) 28);
        genreMap.put("Superhero", (double) 29);

    }

    /*Calculates the total points for the suggestion */
    public static Double calculate(String genre, String rating, String budget){
        Double totalPoints = Double.parseDouble(rating);
        totalPoints += Double.parseDouble(budget.substring(2,budget.length()-1));
        if(genreMap.get(genre.trim())==null){
            totalPoints = 0.0;
        }
        else{
            totalPoints += genreMap.get(genre);
        }

        return totalPoints;
    }
}
