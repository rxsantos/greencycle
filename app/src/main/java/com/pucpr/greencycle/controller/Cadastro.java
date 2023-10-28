package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;

public class Cadastro extends AppCompatActivity {

    EditText Name, Email, Password;
    Button Cadastro;
    RadioButton Coleta, Descarte;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder, Op;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    ContactDatabase database;
    Cursor cursor;
    String F_Result = "Usuário não Encontrado";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Activity Cadastro");

        Name = findViewById(R.id.fullnameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwdEditText);
        Cadastro = (Button) findViewById(R.id.registrarButton);
        Coleta = (RadioButton) findViewById(R.id.coleta);
        Descarte  = (RadioButton) findViewById(R.id.descarte);

        database = new ContactDatabase(this);



        Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //Criar SQLite database se nao existe
                    SQLiteDataBaseBuild();

                    //Cria SQLite table se nao existe
                    SQLiteTableBuild();

                    //Checar EditText se vazio ou nao
                    CheckEditTextStatus();

                    //Metodo para checar se Email ja existe ou nao
                    CheckingEmailAlreadyExistsOrNot();

                    //Verificar se EditText esta vazio apos o processo de insercao
                    EmptyEditTextAfterDataInsert();

            }
        });

    }



    //SQLite database build method
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase("contacts.sqlite",Context.MODE_PRIVATE, null);

    }

    //SQLite table build method
    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("Create Table if not exists "+ "Contact" + "( "+
                "id" + " Integer primary key autoincrement, "+
                "name" + " TEXT, "+
                "email" + " TEXT, "+
                "password" + " TEXT, "+
                "op" + " TEXT);");

    }

    //Insert data into SQLite database method
    public void InsertDataIntoSQLiteDatabase() {
        if (EditTextEmptyHolder) {
            //SQLite query to insert data into table
            SQLiteDataBaseQueryHolder = "INSERT INTO " + "Contact" + " (name,email,password,op) VALUES ('" + NameHolder + "','" + EmailHolder + "','" + PasswordHolder + "','" + Op + "');";

            //Executing query
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            //Closing SQLite database object
            sqLiteDatabaseObj.close();

            //Printing toast message after done insert
            Toast.makeText(Cadastro.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
        }
        // This block will execute if any of the registration EditText is empty.
        else {

            //Printing toast message if any of EditTExt is Empty
            Toast.makeText(Cadastro.this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_LONG).show();
        }

    }
    //Empty editText after done inserting process method
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    //Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        //Getting value from All EditText and storing into String Variables.
        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if (Coleta.isChecked()){
            Op = true;
        }else{
            Op = false;
        }

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false;
        } else{
            EditTextEmptyHolder = true;
        }

    }
        // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
            // Opening SQLite database write permission.
        sqLiteDatabaseObj = database.getWritableDatabase();

            // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query("Contact",null,"" + "email" + "=?",new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()){
                if (cursor.isFirst()){
                    cursor.moveToFirst();

                    // If Email is already exists then Result variable value set as Email Found.
                    F_Result = "Email Encontrado";

                    //Closing cursor.
                    cursor.close();
                }
            }
            CheckFinalResult();
        }


        public void CheckFinalResult(){
            // Checking whether email is already exists or not.
        if (F_Result.equalsIgnoreCase("Email Encontrado")){

            // If email is exists then toast msg will display.
            Toast.makeText(Cadastro.this,"Email já existe. Utilize outro email!",Toast.LENGTH_LONG).show();
        }else{
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
            F_Result = "Não Encontrado";
        }

}



