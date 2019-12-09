package com.company;

import edu.duke.*;

import java.sql.SQLOutput;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + fileName);

        for(CSVRecord record : fr.getCSVParser()){
            String movieID = record.get(0);
            String movieTitle = record.get(1);
            String year = record.get(2);
            String country = record.get(3);
            String genres = record.get(4);
            String director = record.get(5);
            int minutes = Integer.parseInt(record.get(6));
            String poster = record.get(7);

            Movie newMovie = new Movie(movieID, movieTitle, year, genres, director, country, poster, minutes);
            movies.add(newMovie);

            // System.out.println("Movie title: " + movieTitle);
        }
        System.out.println("Total number of movies: " + movies.size());
        return movies;
    }

    public ArrayList<Rater> loadRaters(String fileName){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/" + fileName);

        for(CSVRecord record : fr.getCSVParser()){
            String raterID = record.get(0);
            String movieID = record.get(1);
            double rating = Double.parseDouble(record.get(2));
            boolean isAdded = false;

            for(Rater rater : raters){
                if(raterID.equals(rater.getID())){
                    rater.addRating(movieID, rating);
                    isAdded = true;
                }
            }

            if(!isAdded){
                Rater newRater = new Rater(raterID);
                newRater.addRating(movieID, rating);
                raters.add(newRater);
            }
        }
        System.out.println("Total number of raters: " + raters.size());
        return raters;
    }

    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");

        String directorName = "Woody Allen";
        int countCommedy = 0;
        int countLength = 0;
        int directorCount = 0;
        int maxDirectors = 0;
        int moviesWithNumDirectors = 0;
        int numDirectors = 1;

        for(Movie movie : movies){
            String genres = movie.getGenres();
            int minutes = movie.getMinutes();
            String director = movie.getDirector();
            int currentDirectorCount = 1;

            // Counting genres
            if(genres.contains("Comedy")){
                countCommedy++;
            }

            // Counting movie lengths
            if(minutes > 150){
                countLength++;
            }

            // Counting movies from a specific director
            if(director.contains(directorName)){
                directorCount++;
                // System.out.println(director);
            }

            // Counting how many director what in each film
            while(director.contains(",")){
                currentDirectorCount++;
                director = director.substring(director.indexOf(" ") + 1);
            }

            if(currentDirectorCount == numDirectors){
                moviesWithNumDirectors++;
            }

            // Updating maxDirectors
            if(currentDirectorCount > maxDirectors){
                maxDirectors = currentDirectorCount;
            }
        }

        System.out.println("Number of Comedy movies: " + countCommedy);
        System.out.println("Number of movies longer than 150 minutes: " + countLength);
        System.out.println("Number of movies with " + directorName + " is: " + directorCount);
        System.out.println("Max number of directors: " + maxDirectors);
        System.out.println("Movies with " + numDirectors + " directors is " + moviesWithNumDirectors);

    }

    public void testLoadRaters(){
        ArrayList<Rater> raters = loadRaters("ratings.csv");

        String raterID = "193";
        int raterCount = 0;
        int maxRatings = 0;
        int ratingAmountToFind = 314;
        int raterCountWithRatingAmount = 0;
        String movieID = "1798709";
        int movieCount = 0;
        ArrayList<String> ratedMovies = new ArrayList<String>();

        for(Rater rater : raters) {
            // System.out.println("Rater ID = " + rater.getID() + " and have " + rater.numRatings() + " ratings.");

            // Count ratings for raterID
            if (rater.getID().equals(raterID)) {
                raterCount = rater.numRatings();
            }

            // Find maximum amount of ratings
            int numRatings = rater.numRatings();
            if (numRatings > maxRatings) {
                maxRatings = numRatings;
            }

            // Find how many raters have X ratings
            if (numRatings == ratingAmountToFind){
                raterCountWithRatingAmount++;
                System.out.println("Max rater ID: " + rater.getID());
            }

            if(rater.hasRating(movieID)){
                movieCount++;
            }

            // Counting how many movies was rated
            ArrayList<String> currentMovies = rater.getItemsRated();

            for(int i = 0; i < currentMovies.size(); i++){
                String movie = currentMovies.get(i);
                if(!ratedMovies.contains(movie)){
                    ratedMovies.add(movie);
                }
            }
        }

        System.out.println("Rater " + raterID + " have " + raterCount + " ratings");
        System.out.println("Max rating count is: " + maxRatings);
        System.out.println("Number of raters with " + ratingAmountToFind + " ratings are " + raterCountWithRatingAmount);
        System.out.println("Movie was rated by " + movieCount + " raters");
        System.out.println("Total movies rated: " + ratedMovies.size());
    }
}
