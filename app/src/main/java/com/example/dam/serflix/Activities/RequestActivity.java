package com.example.dam.serflix.Activities;

import android.app.DatePickerDialog;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dam.serflix.R;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtnDate, imgBtnHour;
    TextView dateText, hourText;
    private int day, month, year, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        imgBtnDate = (ImageButton) findViewById(R.id.imgBtnDate);
        imgBtnHour = (ImageButton) findViewById(R.id.imgBtnHour);

        dateText = (TextView) findViewById(R.id.dateText);
        hourText = (TextView) findViewById(R.id.hourText);

        imgBtnHour.setOnClickListener(this);
        imgBtnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == imgBtnDate){
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
        if (v == imgBtnHour){
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hourText.setText(hourOfDay+":"+minute);
                }
            },hour, minute,false);
            timePickerDialog.show();
        }
    }
}
