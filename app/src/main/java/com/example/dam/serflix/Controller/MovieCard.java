package com.example.dam.serflix.Controller;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.example.dam.serflix.R;

public class MovieCard extends CardView{
    public MovieCard(Context context) {
        super(context);
        inflate(getContext(), R.layout.movie_card, this);
    }

    public MovieCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.movie_card, this);

    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.movie_card, this);
    }
}