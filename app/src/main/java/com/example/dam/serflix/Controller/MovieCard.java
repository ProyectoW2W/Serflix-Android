package com.example.dam.serflix.Controller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    //Swipe
    private float initialPositionHorizontal, finalPositionHorizontal, initialPositionVertical, finalPositionVertical;
    static final int MIN_DISTANCE_VERTICAL = 300;
    static final int MIN_DISTANCE_HORIZONTAL = 150;
    static final int MIN_DIFFERENCE = 210;


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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //Horizontal
                initialPositionHorizontal = event.getX();
                //Vertical
                initialPositionVertical = event.getY();
                //break;
                return true;
            case MotionEvent.ACTION_UP:
                //Horizontal
                finalPositionHorizontal = event.getX();
                float differenceHorizontal = finalPositionHorizontal - initialPositionHorizontal;
                //Vertical
                finalPositionVertical = event.getY();
                float differenceVertical = finalPositionVertical - initialPositionVertical;
                //If the horizontal difference is bigger than the minimum means it is a horizontal swipe, else vertical
                if(Math.abs(differenceHorizontal) > MIN_DIFFERENCE){
                    //Swipe horizontal
                    if (Math.abs(differenceHorizontal) > MIN_DISTANCE_HORIZONTAL)
                    {
                        // Left to Right swipe
                        if (finalPositionHorizontal > initialPositionHorizontal)
                        {
                            Toast.makeText(this.getContext(), "Pelicula aceptada", Toast.LENGTH_SHORT).show ();
                        }
                        // Right to left swipe
                        else
                        {
                            Toast.makeText(this.getContext(), "Pelicula rechazada", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }else{
                    //Swipe vertical
                    if (Math.abs(differenceVertical) > MIN_DISTANCE_VERTICAL){
                        if (finalPositionVertical > initialPositionVertical){
                            Toast.makeText(this.getContext(), "Vista y no me ha gustado", Toast.LENGTH_SHORT).show ();
                        }else{
                            Toast.makeText(this.getContext(), "Vista y me ha gustado", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }
                break;
            default:
                //Drag
                break;
        }
        return super.onTouchEvent(event);
    }
}