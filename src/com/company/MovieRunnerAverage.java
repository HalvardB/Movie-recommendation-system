package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");

        System.out.println("Movie size: " + sr.getMovieSize());
        System.out.println("Rater size: " + sr.getRaterSize());

        // Printing sorted list of averages
        ArrayList<Rating> list = sr.getAverageRatings(11);
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " - " + sr.getTitle(rating.getItem()));
        }

        System.out.println("List size is: " + list.size());
        // System.out.println("ID: " + sr.getID("The Godfather"));
        // System.out.println("Title: " + sr.getTitle("0068646"));
    }

    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movieTitle = "Vacation";

        String id = sr.getID(movieTitle);
        Double average = sr.getAverageByID(id, 0);

        System.out.println(movieTitle + "'s average rating is " + average);
    }
}
