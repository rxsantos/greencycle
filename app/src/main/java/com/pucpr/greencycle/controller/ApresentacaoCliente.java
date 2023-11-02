package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

public class ApresentacaoCliente extends AppCompatActivity {

    TextView Name;
    String NameHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_cliente);
        setTitle("Activity Boas Vindas Cliente");
        Name = findViewById(R.id.tvClientName);
        DataModel.getInstance().createDatabase(ApresentacaoCliente.this);

        NameHolder = getIntent().getStringExtra(Login.UserEmail);
        Name.setText(NameHolder+"!");
    }
}