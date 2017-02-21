package com.example.dam.serflix.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dam.serflix.Controller.DatePickerView;
import com.example.dam.serflix.Controller.TimePickerView;
import com.example.dam.serflix.R;

import java.util.Date;

public class RequestActivity extends AppCompatActivity{

    private static final String DIALOG_COMPANY = "RequestActivity.CompanyDialogClass";
    private Button sendRequestButton;

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
                System.out.println("Company: "+companySpinner.getSelectedItem());
                System.out.println("Day: "+datePickerView.getDate());
                System.out.println("Time: "+timePickerView.getTime());
            }
        });
    }
}
