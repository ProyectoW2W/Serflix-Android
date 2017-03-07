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
    private float x1,x2;
    static final int MIN_DISTANCE = 150;


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
                System.out.println("TOUCHEVENT1");
                x1 = event.getX();
                //break;
                return true;
            case MotionEvent.ACTION_UP:
                System.out.println("TOUCHEVENT2");
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        Toast.makeText(this.getContext(), "Pelicula aceptada", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this.getContext(), "Pelicula rechazada", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
            default:
                System.out.println("TOUCHEVENT3");
                break;
        }
        return super.onTouchEvent(event);
    }
}