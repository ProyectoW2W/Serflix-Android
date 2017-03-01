package com.example.dam.serflix.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dam.serflix.Controller.MovieCard;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;

public class RecommendationActivity extends AppCompatActivity {
    MovieCard movieCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        movieCard = (MovieCard)findViewById(R.id.movieCard);
        System.out.println("tal");
        Movie m = new Movie();
        m.setPoster("https://image.tmdb.org/t/p/w1280/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg");
        m.setCast("Jennifer Lawrence, Chris Pratt, Morten Tyldum, Jon Spaihts");
        m.setDescription("A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.");
        m.setTags("Adventure, Drama, Romance, Science Fiction, isolation, spaceship, space");
        m.setTitle("Passengers");
        m.setYear("2016");
        movieCard.setMovieCard(m);
    }
}
