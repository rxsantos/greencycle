package com.pucpr.greencycle.controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

import java.util.Calendar;

public class FormColetas extends AppCompatActivity {
    FormColetasAdapter adapter = new FormColetasAdapter();
    RecyclerView recyclerViewColetas;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_formcoletas);
        setTitle("Activity Retirada de Coletas");
        recyclerViewColetas = findViewById(R.id.recyclerViewColetas);
        btnDatePicker = (Button) findViewById(R.id.btnDatePiker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);


        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnDatePicker) {
                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(FormColetas.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnTimePicker) {

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(FormColetas.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txtTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }

            }
        });


        DataModel.getInstance().createClientDatabase(FormColetas.this);
        recyclerViewColetas.setAdapter(adapter);
        recyclerViewColetas.setLayoutManager(
                new LinearLayoutManager(FormColetas.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                FormColetas.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewColetas.addItemDecoration(itemDecoration);
    }

}
