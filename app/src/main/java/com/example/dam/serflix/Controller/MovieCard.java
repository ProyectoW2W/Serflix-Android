package com.example.dam.serflix.Controller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;
import com.squareup.picasso.Picasso;


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
    }

    public void setMovieCard(Movie m) {
        Picasso.with(this.getContext()).load(m.getPoster()).into(image);
        title.setText(m.getTitle());
        year.setText(m.getYear());
        description.setText(m.getDescription());
        cast.setText(m.getCast());
        tags.setText(m.getTags());
    }

    public Movie getMovie(MovieCard mc){
        Movie movie = new Movie();
        movie.setTags(mc.getTags().toString());
        movie.setTitle(mc.getTitle().toString());
        movie.setYear(mc.getYear().toString());
        movie.setCast(mc.getCast().toString());
        movie.setDescription(mc.getDescription().toString());
        movie.setPoster(mc.getImage().toString());
        return movie;
    }

    public TextView getTags() {
        return tags;
    }

    public void setTags(TextView tags) {
        this.tags = tags;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getYear() {
        return year;
    }

    public void setYear(TextView year) {
        this.year = year;
    }

    public TextView getCast() {
        return cast;
    }

    public void setCast(TextView cast) {
        this.cast = cast;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}