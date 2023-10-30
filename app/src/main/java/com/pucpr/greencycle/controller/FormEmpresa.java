package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

import com.pucpr.greencycle.R;

public class FormEmpresa extends AppCompatActivity {

    RadioButton collector, delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_empresa);
        setTitle("Activity Formulário Empresa");
        //collector = (RadioButton) findViewById(R.id.editRadioOp1);
        //delivery = (RadioButton) findViewById(R.id.editRadioOp2);



        //delivery.setChecked(true);
        //Boolean RadioButtonState = delivery.isChecked();


        //RadioButton simpleRadioButton = (RadioButton) findViewById(R.id.editRadioOp1); // initiate a radio button
        //Boolean RadioButtonState = simpleRadioButton.isChecked(); // check current state of a radio button (true or false).
    }


}