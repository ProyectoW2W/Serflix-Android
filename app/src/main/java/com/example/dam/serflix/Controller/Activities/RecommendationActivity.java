package com.example.dam.serflix.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dam.serflix.Controller.MovieCard;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity {
    MovieCard movieCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        movieCard = (MovieCard)findViewById(R.id.movieCard);


    }

    public List<Movie> pruebaMovies(){
        Movie m = new Movie();
        m.setPoster("https://image.tmdb.org/t/p/w1280/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg");
        m.setCast("Jennifer Lawrence, Chris Pratt, Morten Tyldum, Jon Spaihts");
        m.setDescription("A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.");
        m.setTags("Adventure, Drama, Romance, Science Fiction, isolation, spaceship, space");
        m.setTitle("Passengers");
        m.setYear("2016");

        Movie m2 = new Movie();
        m2.setPoster("https://image.tmdb.org/t/p/w1280/45Y1G5FEgttPAwjTYic6czC9xCn.jpg");
        m2.setCast("Hugh Jackman, Patrick Stewart, Dafne Keen, James Mangold");
        m2.setDescription("In the near future, a weary Logan cares for an ailing Professor X in a hide out on the Mexican border. But Logan's attempts to hide from the world and his legacy are up-ended when a young mutant arrives, being pursued by dark forces.");
        m2.setTags("Marvel, Superheroes, X-Men, Action, Drama, Science fiction, mutant");
        m2.setTitle("Logan");
        m2.setYear("2017");

        List<Movie> movies = new ArrayList<>();
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        movies.add(m);
        movies.add(m2);
        return movies;
    }
}
