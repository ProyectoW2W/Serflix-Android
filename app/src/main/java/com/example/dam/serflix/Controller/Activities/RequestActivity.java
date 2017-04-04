package com.example.dam.serflix.Controller.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.dam.serflix.Controller.DatePickerView;
import com.example.dam.serflix.Controller.TimePickerView;
import com.example.dam.serflix.R;

import java.util.Date;

public class RequestActivity extends AppCompatActivity {


    private Button sendRequestButton;

    /*
    private Button LocationButton;
    private TextView CoordinatesText;
    private LocationManager locationManager;
    private LocationListener locationListener;
    */
    private Timer timer;
    private LocationManager locationManager;
    //private LocationGetter.LocationResult locationResult;
    private boolean gpsEnabled = false;
    private boolean networkEnabled = false;



    Spinner companySpinner;
    TimePickerView timePickerView;
    DatePickerView datePickerView;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        datePickerView = (DatePickerView) findViewById(R.id.datePickerView);
        timePickerView = (TimePickerView) findViewById(R.id.timePickerView);
        companySpinner = (Spinner) findViewById(R.id.companySpinner);
        sendRequestButton = (Button) findViewById(R.id.sendRequestButton);

        Bundle extras = getIntent().getExtras();
        String latlon = extras.getString("latlon");
        System.out.println(latlon);


        //Establecer valores Company Spinner
        Spinner spinner = (Spinner) findViewById(R.id.companySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.company_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //Listeners
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Company: " + companySpinner.getSelectedItem());
                System.out.println("Day: " + datePickerView.getDate());
                System.out.println("Time: " + timePickerView.getTime());

                //Enviar request

                //Pasar a recommendationActivity
                Intent intent = new Intent(RequestActivity.this, RecommendationActivity.class);
                startActivity(intent);
            }
        });
    }
}