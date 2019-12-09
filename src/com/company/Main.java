package com.company;

public class Main {

    public static void main(String[] args) {
        FirstRatings loadRatings = new FirstRatings();
        // loadRatings.loadMovies("ratedmoviesfull.csv");
        loadRatings.testLoadMovies();
        loadRatings.testLoadRaters();
    }
}
