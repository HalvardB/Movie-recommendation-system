package com.company;

import java.util.*;
import java.util.function.DoubleBinaryOperator;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    private HashMap<String, ArrayList<Double>> movieRatings;
    private ArrayList<Rating> averageRatingsAbove;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingFile){
        FirstRatings ratings = new FirstRatings();
        myRaters = ratings.loadRaters(ratingFile);
        myMovies = ratings.loadMovies(movieFile);
        movieRatings = new HashMap<String, ArrayList<Double>>();
        createHashMapOfMovies();

        // getAverageByID("0068646",5);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private void createHashMapOfMovies(){

        // Add all movies to the movieRatings array as Keys
        for(Movie movie : myMovies){
            String movieID = movie.getID();
            ArrayList<Double> ratings = new ArrayList<Double>();
            movieRatings.put(movieID, ratings);
        }

        // Add all ratings to the movieRatings array
        for(Rater rater : myRaters){
            ArrayList<String> moviesRated = rater.getItemsRated();

            for(int i = 0; i < moviesRated.size(); i++){
                String movieID = moviesRated.get(i);
                Double rating = rater.getRating(movieID);
                ArrayList<Double> allRatings = movieRatings.get(movieID);
                allRatings.add(rating);

                movieRatings.put(movieID, allRatings);
            }
        }
    }

    public double getAverageByID(String id, int minimalRaters){
        Double average = 0.0;

        for(Movie movie : myMovies){
            String movieID = movie.getID();
            ArrayList<Double> ratingList = movieRatings.get(movieID);

            if(movieID.equals(id) && ratingList.size() >= minimalRaters){
                Double total = 0.0;
                int ratingCount = 0;

                for(int i = 0; i < ratingList.size(); i++){
                    total = total + ratingList.get(i);
                    ratingCount++;
                }

                average = total / ratingCount;
                // System.out.println("Average rating is: " + average + " from " + ratingCount + " reviewers");
                return average;
            }
        }

        // System.out.println("Average rating is 0.0 - did not qualify");
        return average;
    }

    // Create and return an array of movies with more than x ratings.
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        averageRatingsAbove = new ArrayList<Rating>();

        for(String movieID : movieRatings.keySet()){
            Double averageRating = getAverageByID(movieID, minimalRaters);
            if(averageRating != 0.0){
                Rating rating = new Rating(movieID, averageRating);
                averageRatingsAbove.add(rating);
            }
        }
        return averageRatingsAbove;
    }

    public String getTitle(String id){
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return "ID not found";
    }

    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "ID not found";
    }






}
