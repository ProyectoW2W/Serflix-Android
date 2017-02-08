package com.example.dam.serflix.Controller.Activities;

import android.app.DatePickerDialog;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dam.serflix.R;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DIALOG_COMPANY = "RequestActivity.CompanyDialogClass";
    private Button company_btn;

    TextView dateText, hourText;
    private int day, month, year, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        company_btn = (Button) findViewById(R.id.company_btn);
        dateText = (TextView) findViewById(R.id.dateTextPicker);
        hourText = (TextView) findViewById(R.id.hourTextPicker);

        company_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyDialogClass dialogClass = new CompanyDialogClass();
                dialogClass.show(getSupportFragmentManager(), DIALOG_COMPANY);
            }
        });

        //Create a Calendar
        final Calendar c = Calendar.getInstance();

        //Current Date
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        dateText.setText(day+"/"+(month+1)+"/"+year);

        //Current Hour
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        if(minute<10){
            hourText.setText(hour+":0"+minute);
        }else {
        hourText.setText(hour+":"+minute);}

        //Make TextView clickable
        hourText.setOnClickListener(this);
        dateText.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == dateText){
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateText.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },day, month, year);
            datePickerDialog.show();
        }
        if (v == hourText){
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(minute<10){
                        hourText.setText(hourOfDay+":0"+minute);
                    }else {
                        hourText.setText(hourOfDay+":"+minute);}
                }
            },hour, minute,false);
            timePickerDialog.show();
        }
    }
}
