package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pucpr.greencycle.R;

public class ListUsers extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listusers_admin);
        setTitle("Activity Lista Usuarios");
        recyclerView = findViewById(R.id.recyclerView);
    }
}