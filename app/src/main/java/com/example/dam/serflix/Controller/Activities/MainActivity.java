package com.example.dam.serflix.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dam.serflix.Model.Location;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;
import com.example.dam.serflix.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request(Type.MOVIE,new Date(2017,02,24),new Date(2017,02,25), Company.ALONE,null,new Location(41.5933302,1.835487));
    }


}
