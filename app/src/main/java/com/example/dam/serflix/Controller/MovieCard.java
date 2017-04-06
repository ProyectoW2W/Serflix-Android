package com.example.dam.serflix.Controller;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieCard extends LinearLayout implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
    TextView tags;
    TextView title;
    TextView year;
    TextView cast;
    TextView description;
    ImageView image;
    int position;
    float initialPositionHorizontal, finalPositionHorizontal, initialPositionVertical, finalPositionVertical, cardInitialPositionX, cardInitialPositionY;
    //Swipe
    static final int MIN_DISTANCE_VERTICAL = 300;
    static final int MIN_DISTANCE_HORIZONTAL = 150;
    static final int MIN_DIFFERENCE = 210;
    private MovieCardListener listener;
    List<Movie> movies;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;



    public MovieCard(Context context) {
        super(context);
        this.listener = null;
        movies = new ArrayList<>();
        cardInitialPositionX = getX();
        cardInitialPositionY = getY();
        position = 0;
        mDetector = new GestureDetectorCompat(this.getContext(),this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
        initializeView();
    }

    public MovieCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.listener = null;
        movies = new ArrayList<>();
        cardInitialPositionX = getX();
        cardInitialPositionY = getY();
        position = 0;
        mDetector = new GestureDetectorCompat(this.getContext(),this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
        initializeView();
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.listener = null;
        movies = new ArrayList<>();
        cardInitialPositionX = getX();
        cardInitialPositionY = getY();
        position = 0;
        mDetector = new GestureDetectorCompat(this.getContext(),this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
        initializeView();
    }

    public void swipeRight(){
        position++;
        if (position < this.movies.size()-1){
            if (position == this.movies.size() - 2){
                listener.onOneMovieRemaining();
            }
            setMovieCard(this.movies.get(position));
        }
    }

    public void initializeView() {
        inflate(getContext(), R.layout.movie_card, this);
    }

    public void setCustomObjectListener(MovieCardListener listener) {
        this.listener = listener;
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

    public void newSet(List<Movie> m){
        this.movies.addAll(m);
        Movie a = m.get(0);
        setMovieCard(a);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        this.mDetector.onTouchEvent(event);
        Toast.makeText(this.getContext(),
                "touchevent", Toast.LENGTH_SHORT).show();
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Toast.makeText(this.getContext(),
                "ondown", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Toast.makeText(this.getContext(),
                "onfling", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return true;
    }
    /*
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
                            this.animate()
                                    //.x(getWidth()+500)
                                    .x(getWidth()+400)
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRight();
                                        }
                                    })
                                    .start();
                            listener.onSwipe();
                        }
                        // Right to left swipe
                        else
                        {
                            Toast.makeText(this.getContext(), "Pelicula rechazada", Toast.LENGTH_SHORT).show ();
                            this.animate()
                                    .x(-451-getWidth())
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRight();
                                        }
                                    })
                                    .start();
                            listener.onSwipe();
                        }
                    }
                }else{
                    //Swipe vertical
                    if (Math.abs(differenceVertical) > MIN_DISTANCE_VERTICAL){
                        if (finalPositionVertical > initialPositionVertical){
                            Toast.makeText(this.getContext(), "Vista y no me ha gustado", Toast.LENGTH_SHORT).show ();
                            this.animate()
                                    .y(getHeight()+900)
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRight();
                                        }
                                    })
                                    .start();
                            listener.onSwipe();
                        }else{
                            Toast.makeText(this.getContext(), "Vista y me ha gustado", Toast.LENGTH_SHORT).show ();
                            this.animate()
                                    .y(31-getHeight())
                                    .setDuration(300)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRight();
                                        }
                                    })
                                    .start();
                            listener.onSwipe();
                        }
                    }
                }
                break;
            default:
                //Drag
                this.setX(event.getRawX()-initialPositionHorizontal);
                this.setY(event.getRawY()-initialPositionVertical);
                break;
        }
        return super.onTouchEvent(event);
    }*/

    public interface MovieCardListener {
        public void onSwipe();
        public void onOneMovieRemaining();
    }
}
