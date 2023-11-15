package com.pucpr.greencycle.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

public class FormColetas extends AppCompatActivity {
    FormColetasAdapter adapter = new FormColetasAdapter();
    RecyclerView recyclerViewColetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_formcoletas);
        setTitle("Activity Retirada de Coletas");
        recyclerViewColetas = findViewById(R.id.recyclerViewColetas);
        DataModel.getInstance().createClientDatabase(FormColetas.this);
        recyclerViewColetas.setAdapter(adapter);
        recyclerViewColetas.setLayoutManager(
                new LinearLayoutManager(FormColetas.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                FormColetas.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewColetas.addItemDecoration(itemDecoration);
    }

}
