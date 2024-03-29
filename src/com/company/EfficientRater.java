package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        Rating newRating = new Rating(item, rating);
        myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        return myRatings.get(item).getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();

        for( String rating : myRatings.keySet()){
            list.add(list.size(), rating);
        }
        
        return list;
    }
}
