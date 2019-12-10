package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");

        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatings(35);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " - " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfter(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        YearAfterFilter yearFilter = new YearAfterFilter(2000);

        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, yearFilter);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " - " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        GenreFilter genreFilter = new GenreFilter("Comedy");

        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, genreFilter);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " - " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(" - Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MinutesFilter minutesFilter = new MinutesFilter(105, 135);

        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(5, minutesFilter);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " - title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " - time: " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        DirectorsFilter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");

        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(4, directorsFilter);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " - title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " - directors: " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Adding filters
        AllFilters allFilters = new AllFilters();
        int minimalRaters = 8;

        // Genre filter
        GenreFilter genreFilter = new GenreFilter("Drama");
        allFilters.addFilter(genreFilter);

        // Year filter
        YearAfterFilter yearFilter = new YearAfterFilter(1990);
        allFilters.addFilter(yearFilter);

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " - title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " - year: " + MovieDatabase.getYear(rating.getItem()) +
                    " - genres: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("Rater size: " + tr.getRaterSize());
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // Adding filters
        AllFilters allFilters = new AllFilters();
        int minimalRaters = 3;

        // Directors filter
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        allFilters.addFilter(directorsFilter);

        // Minutes filter
        MinutesFilter minutesFilter = new MinutesFilter(90, 180);
        allFilters.addFilter(minutesFilter);

        // Printing sorted list of averages
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("List size is: " + list.size());
        Collections.sort(list);
        for(Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " - title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " - directors: " + MovieDatabase.getDirector(rating.getItem()) +
                    " - Movie time: " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

}
