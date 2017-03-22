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


        /*
            //botones lat lon
            LocationButton = (Button) findViewById(R.id.buttonLatLon);
            CoordinatesText = (TextView) findViewById(R.id.textLatLon);
        */


        //Establecer valores Company Spinner
        Spinner spinner = (Spinner) findViewById(R.id.companySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.company_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        /*
        //LLAMAR A GPS
        ObtainLatLon();
        */










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
/*
    private void ObtainLatLon() {
        locationManager = (LocationManager) getSystemService((LOCATION_SERVICE));
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                CoordinatesText.append("\n "+location.getLatitude()+" "+location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET
                }, 10);
            }
            return;
        }else{
            configureButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }

    }

    private void configureButton(){
        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
            }
        });
    }

*/






    ////////////////























}