package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

public class Recyclerview extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        setTitle("Activity RecyclerView");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(Recyclerview.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                Recyclerview.this,DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(itemDecoration);

        DataModel.getInstance().createDatabase(Recyclerview.this);

    }
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}