package com.example.dam.serflix.Controller.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;
import com.huxq17.swipecardsview.BaseCardAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergiodiaz on 12/4/17.
 */

public class CardAdapter extends BaseCardAdapter {

    private List<Movie> movieList;
    private Context context;

    public CardAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public int getCardLayoutId() {
        return R.layout.movie_card;
    }

    @Override
    public void onBindData(int position, View cardview) {
        if (movieList == null || movieList.size() == 0){
            return;
        }
        ImageView poster = (ImageView)cardview.findViewById(R.id.poster);
        TextView title = (TextView)cardview.findViewById(R.id.title);
        TextView year = (TextView)cardview.findViewById(R.id.year);
        TextView cast = (TextView)cardview.findViewById(R.id.cast);
        TextView description = (TextView)cardview.findViewById(R.id.description);
        TextView tags = (TextView)cardview.findViewById(R.id.tags);
        Movie movie = movieList.get(position);
        Picasso.with(context).load(movie.getPoster()).into(poster);
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        cast.setText(movie.getCast());
        description.setText(movie.getDescription());
        tags.setText(movie.getCast());
    }
}
