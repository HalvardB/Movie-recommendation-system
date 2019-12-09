package com.company;

public class Main {

    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        // loadRatings.loadMovies("ratedmoviesfull.csv");
        // loadRatings.testLoadMovies();
        // loadRatings.testLoadRaters();

        // SecondRatings sr = new SecondRatings();

        MovieRunnerAverage mra = new MovieRunnerAverage();
        mra.printAverageRatings();
        // mra.getAverageRatingOneMovie();
    }
}
