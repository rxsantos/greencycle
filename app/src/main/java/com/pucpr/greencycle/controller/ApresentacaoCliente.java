package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pucpr.greencycle.R;

public class ApresentacaoCliente extends AppCompatActivity {

    TextView Name;
    String NameHolder, EmailHolder, IdentHolder="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_cliente);
        setTitle("Activity Tela Abertura Cliente");
        Name = findViewById(R.id.tvClientName);
        //DataModel.getInstance().createDatabase(ApresentacaoCliente.this);

        Bundle extras = getIntent().getExtras();
        NameHolder = extras.getString("EXTRA_NAME");
        EmailHolder = extras.getString("EXTRA_EMAIL");
        IdentHolder = extras.getString("EXTRA_USERID");
        //System.out.println(IdentHolder);
        //NameHolder = getIntent().getStringExtra(Login.UserName);
        Name.setText("Olá "+ NameHolder+"!");
    }

    public void solicitarButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoCliente.this, FormCliente.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        //System.out.println(IdentHolder+"PresCliente");
        extras.putString("EXTRA_USERID",IdentHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void listaSolicitacaoButtonOnClick(View v){
        Intent intent = new Intent(ApresentacaoCliente.this, Cliente.class);
        startActivity(intent);
    }
}