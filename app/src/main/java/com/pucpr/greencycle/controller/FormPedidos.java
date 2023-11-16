package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

public class FormPedidos extends AppCompatActivity {

    FormPedidosAdapter adapter = new FormPedidosAdapter();
    RecyclerView recyclerViewPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_formpedidos);
        setTitle("Activity Solicitação de Coletas");
        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        DataModel.getInstance().createCompanyDatabase(FormPedidos.this);
        recyclerViewPedidos.setAdapter(adapter);
        recyclerViewPedidos.setLayoutManager(
                new LinearLayoutManager(FormPedidos.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                FormPedidos.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewPedidos.addItemDecoration(itemDecoration);
    }
}