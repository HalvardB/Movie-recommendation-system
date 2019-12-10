package com.company;

public class Main {

    public static void main(String[] args) {

        // FirstRatings fr = new FirstRatings();
        // fr.loadMovies("ratedmoviesfull.csv");
        // fr.testLoadMovies();
        // fr.testLoadRaters();

        // SecondRatings sr = new SecondRatings();

        // MovieRunnerAverage mra = new MovieRunnerAverage();
        // mra.printAverageRatings();
        // mra.getAverageRatingOneMovie();

        // ThirdRatings tr = new ThirdRatings();
        MovieRunnerWithFilters mrf = new MovieRunnerWithFilters();
        // mrf.printAverageRatingsByYearAfter();
        // mrf.printAverageRatingsByGenre();
        // mrf.printAverageRatingsByMinutes();
        // mrf.printAverageRatingsByDirectors();
        // mrf.printAverageRatingsByDirectorsAndMinutes();
        // mrf.printAverageRatingsByYearAfterAndGenre();

        mrf.printAverageRatingsByDirectorsAndMinutes();
    }
}
