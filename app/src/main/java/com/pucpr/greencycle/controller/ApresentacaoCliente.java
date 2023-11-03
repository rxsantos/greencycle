package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        setTitle("Activity Tela Abertura Cliente");
        Name = findViewById(R.id.tvClientName);
        //DataModel.getInstance().createDatabase(ApresentacaoCliente.this);

        NameHolder = getIntent().getStringExtra(Login.UserName);
        Name.setText("Olá "+ NameHolder+"!");
    }

    public void solicitarButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoCliente.this, FormCliente.class);
        startActivity(intent);
    }

    public void listaSolicitacaoButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoCliente.this, Cliente.class);
        startActivity(intent);
    }
}