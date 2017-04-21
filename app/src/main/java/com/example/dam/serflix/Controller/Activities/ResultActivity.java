package com.example.dam.serflix.Controller.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.R;
import com.squareup.picasso.Picasso;

public class ResultActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        final String posterSt = extras.getString("poster");
        final String titleSt = extras.getString("title");

        ImageView poster = (ImageView)findViewById(R.id.poster);
        Picasso.with(context).load(posterSt).into(poster);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(titleSt);

    }
}
