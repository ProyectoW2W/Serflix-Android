package com.example.dam.serflix.Controller.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.serflix.R;
import com.squareup.picasso.Picasso;

public class ResultActivity extends AppCompatActivity {

    private Context context;
    private ImageButton googlePlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        googlePlayButton = (ImageButton)findViewById(R.id.googlePlayButton);

        Bundle extras = getIntent().getExtras();
        final String posterSt = extras.getString("poster");
        final String titleSt = extras.getString("title");

        ImageView poster = (ImageView)findViewById(R.id.poster);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w1280/"+posterSt).into(poster);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(titleSt);


        googlePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/search?q="+titleSt+"&c=movies&hl=en";
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=" + titleSt)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            }
        });

    }
}
