package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.DataModel;

public class RecyclerviewAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactAdapter adapter = new ContactAdapter();
    Button Adicionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_admin);
        setTitle("Activity RecyclerViewAdmin");
        Adicionar = (Button) findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerView);
        DataModel.getInstance().createDatabase(RecyclerviewAdmin.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(RecyclerviewAdmin.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                RecyclerviewAdmin.this,DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToAddUser(position);
            }
        });

        adapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Contact c = DataModel.getInstance().getContact(position);
                DataModel.getInstance().removeContact(position);
                adapter.notifyItemRemoved(position);
                View contextView = findViewById(android.R.id.content);
                Snackbar.make(contextView,R.string.remove_contact,Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataModel.getInstance().insertContact(c,position);
                                adapter.notifyItemInserted(position);
                            }
                        }).show();
                return true;
            }
        });


    }
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    void goToAddUser(int index){
        Intent intent = new  Intent(RecyclerviewAdmin.this, UpdateUsers.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }
    public void addButtonOnClick(View v){
        Intent intent = new  Intent(RecyclerviewAdmin.this, AddUsers.class);
        startActivity(intent);
    }

}