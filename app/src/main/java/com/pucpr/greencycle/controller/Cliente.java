package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.ContactDatabase;

import java.util.ArrayList;

public class Cliente extends AppCompatActivity {
    ContactDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listorder_cliente);
        setTitle("Activity Cliente");


    }
}