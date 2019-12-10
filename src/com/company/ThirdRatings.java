package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID(String movieID, int minimalRaters){
        int ratersCounter = 0;
        double totalRatings = 0.0;
        for (EfficientRater rater: myRaters){

            if (rater.hasRating(movieID)){
                ratersCounter++;
                totalRatings+=rater.getRating(movieID);
            }
        }
        if (ratersCounter >= minimalRaters){
            return totalRatings / (double) ratersCounter;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result= new ArrayList<>();
        ArrayList<String> movieIdRated = new ArrayList<>();

        for (EfficientRater current : myRaters) {
            movieIdRated.addAll(current.getItemsRated());
        }

        Map<String, Integer> moviesFrequencyMap = new HashMap<>();
        for (String movie: movieIdRated){
            moviesFrequencyMap.put(movie, Collections.frequency(movieIdRated, movie));
        }

        ArrayList<String> minimalRatersMoviesList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: moviesFrequencyMap.entrySet()){
            if (entry.getValue() >= minimalRaters){
                minimalRatersMoviesList.add(entry.getKey());
            }
        }

        for (String movie: minimalRatersMoviesList){
            result.add(new Rating(movie, getAverageByID(movie, minimalRaters)));
        }
        return result;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<Rating> avrRating = new ArrayList<>();
        avrRating = getAverageRatings(minimalRaters);

        for (Rating rating : avrRating) {
            if (filterCriteria.satisfies(rating.getItem())){
                result.add(rating);
            }
        }

        return result;
    }
}

