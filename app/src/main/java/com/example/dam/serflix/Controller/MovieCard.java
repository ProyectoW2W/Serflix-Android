package com.example.dam.serflix.Controller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;


public class MovieCard extends LinearLayout {
    TextView tags;
    TextView title;
    TextView year;
    TextView cast;
    TextView description;
    ImageView image;


    public MovieCard(Context context) {
        super(context);
        initializeView();
    }

    public MovieCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();

    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    public void initializeView() {
        inflate(getContext(), R.layout.movie_card, this);
    }

    @Override
    protected void onFinishInflate() {
        tags = (TextView) findViewById(R.id.tags);
        title = (TextView) findViewById(R.id.title);
        year = (TextView) findViewById(R.id.year);
        cast = (TextView) findViewById(R.id.cast);
        description = (TextView) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.poster);
        super.onFinishInflate();

        Movie m = new Movie();
        m.setCast("Jennifer Lawrence, Chris Pratt, Morten Tyldum, Jon Spaihts");
        m.setDescription("A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.");
        m.setTags("Adventure, Drama, Romance, Science Fiction, isolation, spaceship, space");
        m.setTitle("Passengers");
        m.setYear("2016");
        setMovieCard(m);
    }

    public void setMovieCard(Movie m) {
        //image.setImageResource();
        title.setText(m.getTitle());
        year.setText(m.getYear());
        description.setText(m.getDescription());
        cast.setText(m.getCast());
        tags.setText(m.getTags());
    }
}