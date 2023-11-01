package com.pucpr.greencycle.controller;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.pucpr.greencycle.R;

public class FormCliente extends AppCompatActivity {

    RadioButton collector, delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);
        setTitle("Activity Formulário Cliente");

    }


}