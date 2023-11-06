package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.R.id;

public class ApresentacaoEmpresa extends AppCompatActivity {

    TextView Name;
    String NameHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_empresa);
        setTitle("Activity Tela Abertura Empresa");
        Name = findViewById(id.tvEmpresaName);
        //DataModel.getInstance().createDatabase(ApresentacaoEmpresa.this);

        NameHolder = getIntent().getStringExtra(Login.UserName);
        Name.setText("Olá "+ NameHolder+"!");
    }

    public void retirarButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoEmpresa.this, FormEmpresa.class);
        startActivity(intent);
    }

    public void listaRetirardasButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoEmpresa.this, Empresa.class);
        startActivity(intent);
    }
}