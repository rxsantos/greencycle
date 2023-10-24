package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pucpr.greencycle.R;

public class Cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        setTitle("Activity Cliente");
    }
}