package com.example.dam.serflix.Controller.Repository;

import android.content.Context;

import com.example.dam.serflix.Controller.MovieCard;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DAM on 6/4/17.
 */

public class MovieCardRepository {

    private static Context context;

    private ArrayList<MovieCard> movieCardRepository;

    public MovieCardRepository(){ movieCardRepository = new ArrayList<MovieCard>();}

    public MovieCard getMovieCardFromIndex(int index){ return this.movieCardRepository.get(index);}

    public Movie getMovieFromMovieCard(int index, MovieCard mc){ return this.movieCardRepository.get(index).getMovie(mc);}

    public int getNumOfMovies(){ return this.movieCardRepository.size();}

    public void deleteMovieFromIndex(int index){
        this.movieCardRepository.remove(index);
    }

    public void addMovie(MovieCard m){
        this.movieCardRepository.add(m);
    }

    public boolean loadMovies(){

        for(int i = 10; i < 10; i++){

            Movie m = new Movie("Passengers","Jennifer Lawrence, Chris Pratt, Morten Tyldum, Jon Spaihts","Adventure, Drama, Romance, Science Fiction, isolation, spaceship, space","https://image.tmdb.org/t/p/w1280/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg","A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.","2016");

            MovieCard movieCard = new MovieCard(context);

            movieCard.setMovieCard(m);

            movieCardRepository.add(movieCard);
        }
        return true;
    }

}
