package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;

public class Cadastro extends AppCompatActivity {

    EditText Name, Email, Password;
    Button Cadastro;
    RadioButton coleta, descarte;
    int index;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Activity Cadastro");
        Name = findViewById(R.id.fullnameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwdEditText);
        Cadastro = (Button) findViewById(R.id.registrarButton);
        coleta = (RadioButton) findViewById(R.id.coleta);
        descarte  = (RadioButton) findViewById(R.id.descarte);

    }

    ContactDatabase database = new ContactDatabase(this);





    public void cadastroButtonOnClick(View v){
        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (email.length() > 1 && password.length() > 1){
            if (index == -1){

                DataModel.getInstance().addContact(
                        new Contact(name,email,password)
                );
            }
            finish();
        }else{
        AlertDialog.Builder builder = new AlertDialog.Builder(Cadastro.this);
        builder.setTitle(R.string.attention);
        builder.setMessage(R.string.empty_contact_alert_msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no,null);
        builder.create().show();

        }
    }
}