package com.company;

public class DirectorsFilter implements Filter {

    private String[] requiredDirectors;

    public DirectorsFilter(String directorsList){
        requiredDirectors = directorsList.split(",");
    }

    @Override
    public boolean satisfies(String id){
        String[] movieDirectors = MovieDatabase.getDirector(id).split(",");

        for (int i = 0; i < movieDirectors.length; i++){
            movieDirectors[i] = movieDirectors[i].trim();
        }

        for (String currRequired: requiredDirectors){
            for (String currMovieDirs: movieDirectors) {
                if (currMovieDirs.equals(currRequired)) {
                    return true;
                }
            }
        }
        return false;
    }
}
