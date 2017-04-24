package com.example.dam.serflix.Controller.Activities;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dam.serflix.Controller.DatePickerView;
import com.example.dam.serflix.Controller.Managers.RequestCallback;
import com.example.dam.serflix.Controller.Managers.RequestManager;
import com.example.dam.serflix.Controller.TimePickerView;
import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;
import com.example.dam.serflix.R;

import java.sql.SQLOutput;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

public class RequestActivity extends AppCompatActivity implements RequestCallback {


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
        final String latlon = extras.getString("latlon");
        System.out.println(latlon);


        //Establecer valores Company Spinner
        Spinner spinner = (Spinner) findViewById(R.id.companySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.company_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        datePickerView.setDate(Calendar.getInstance().getTime());

        //Listeners
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Company: " + companySpinner.getSelectedItem());
                System.out.println("Day: " + datePickerView.getDate());
                System.out.println("Time: " + timePickerView.getTime());
                Date tiempo = datePickerView.getDate();
                long milis = tiempo.getTime();
                milis += timePickerView.getTime().getTime();
                tiempo.setTime(milis);


                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
                String fechaStr = formatter.format(tiempo);
                String timeNow = formatter.format(Calendar.getInstance().getTime());

//                if(timeNow==null){
//                    timePickerView.setTime(Time.valueOf(formatter.format(tiempo)));
//                }

                //Enviar request
                //Type type, String viewDate, String creationDate, Company company, String location
                Request request = new Request(Type.MOVIE, fechaStr, timeNow, Company.ALONE, latlon);
                RequestManager.getInstance().setRequest(request);
                RequestManager.getInstance().createRequest(RequestActivity.this, request);
                //Pasar a recommendationActivity
                Intent intent = new Intent(RequestActivity.this, RecommendationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccessMR(List<MovieRecommendation> requestsList) {
        Log.d("Alberto","Funciona. Pendiente de probar!!");
    }

    @Override
    public void onSuccess(List<Request> requestsList) {

    }

    @Override
    public void onSuccess(Request request) {

        RequestManager.getInstance().getRecomendations(RequestActivity.this, request);

    }

    @Override
    public void onFailure(Throwable t) {

    }
}