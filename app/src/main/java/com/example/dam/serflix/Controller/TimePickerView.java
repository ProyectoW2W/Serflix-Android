package com.example.dam.serflix.Controller;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.dam.serflix.R;

import java.sql.Time;
import java.util.Calendar;

public class TimePickerView extends EditText implements TimePickerDialog.OnTimeSetListener{
    private Time time;

    //  private Date previousSelectedDate;

    public TimePickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TimePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes();
    }

    public TimePickerView(Context context) {
        super(context);
        setAttributes();
    }


    private void setAttributes() {

        setHint(R.string.timePickerView);
        setGravity(Gravity.LEFT | Gravity.CENTER);
        setFocusable(false);
        // setTextSize(18);
        //  setPadding(10, 10, 10, 10);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (time != null) {
                    calendar.setTime(time);
                }
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TimePickerView.this.getContext(), TimePickerView.this,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                );

                // datePicker.setCanceledOnTouchOutside(true);
                timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.dismiss();
                        }
                    }
                });


                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}