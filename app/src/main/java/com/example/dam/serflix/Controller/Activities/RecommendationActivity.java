package com.example.dam.serflix.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dam.serflix.Controller.Adapter.CardAdapter;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;
import com.huxq17.swipecardsview.SwipeCardsView;

import java.util.ArrayList;
import java.util.List;


public class RecommendationActivity extends AppCompatActivity {

    private SwipeCardsView swipeCardsView;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        swipeCardsView = (SwipeCardsView)findViewById(R.id.swipeCardsView);
        swipeCardsView.retainLastCard(false);
        swipeCardsView.enableSwipe(true);
        getData();
    }

    private void getData() {
        movieList.add(new Movie("Victor","Victor Amador","Monster","https://lh6.ggpht.com/J6KO2dcu2o-jl1Mcd2oeiSBvsfwKax6eRqSxXYkMd8OxfC-KCrfZgHwXgPE2Qkbr8yY=w300","Crash Bandicut","1996"));
        movieList.add(new Movie("Fernando","Fernando Aranda","Shortcuts","https://lh6.ggpht.com/J6KO2dcu2o-jl1Mcd2oeiSBvsfwKax6eRqSxXYkMd8OxfC-KCrfZgHwXgPE2Qkbr8yY=w300","Mira este shortcut","1996"));
        movieList.add(new Movie("Larry","Larry Sanchez","Magic The Gathering","https://lh6.ggpht.com/J6KO2dcu2o-jl1Mcd2oeiSBvsfwKax6eRqSxXYkMd8OxfC-KCrfZgHwXgPE2Qkbr8yY=w300","Yo antes pintaba fabricas","1996"));
        movieList.add(new Movie("Alberto","Alberto Comuñas","Dark Souls","https://lh6.ggpht.com/J6KO2dcu2o-jl1Mcd2oeiSBvsfwKax6eRqSxXYkMd8OxfC-KCrfZgHwXgPE2Qkbr8yY=w300","Las VANS son las mejores bambas","1996"));
        movieList.add(new Movie("Sergio","Sergio Díaz","Salvapantallas","https://lh6.ggpht.com/J6KO2dcu2o-jl1Mcd2oeiSBvsfwKax6eRqSxXYkMd8OxfC-KCrfZgHwXgPE2Qkbr8yY=w300","Mira como se escucha","1996"));

        CardAdapter cardAdapter = new CardAdapter(movieList, this);
        swipeCardsView.setAdapter(cardAdapter);

    }


}


