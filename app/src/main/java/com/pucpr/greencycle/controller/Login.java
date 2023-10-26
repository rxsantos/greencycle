package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button Login, Cadastrar;
    EditText Email, Password;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    String TempPassword = "Usuário não Encontrado";
    public static final String UserEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Activity Login");
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Login = (Button) findViewById(R.id.loginButton);
        Cadastrar = (Button) findViewById(R.id.cadastrarButton);

        //DataModel.getInstance().createDatabase(getApplicationContext());
        database = new ContactDatabase(this);

        ArrayList<Contact>contacts = database.getContactsFromDB();
        for (Contact c:contacts) {
            c.print();
        }



    }
    public void loginButtonOnClick(View v){


        // Calling EditText is empty or no method.
        CheckEditTextStatus();

        // Calling login method.
        LoginFunction();

    }


    public void registerButtonOnClick(View v){
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }
    public void recoverButtonOnClick(View v){
        Intent intent = new Intent(Login.this, RecuperarSenha.class);
        startActivity(intent);
    }


    // Login function starts from here.
    public void LoginFunction(){
        if(EditTextEmptyHolder) {
            // Opening SQLite database write permission.
            sqLiteDatabaseObj = database.getWritableDatabase();

            // Adding search email query to cursor.
            Cursor cursor = sqLiteDatabaseObj.query("Contact", null, " " + "email" + "=?", new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();
        }
        else {
            //If any of login EditText empty then this block will be executed.
            Toast.makeText(Login.this,"Digite o nome de usuário ou a senha.",Toast.LENGTH_LONG).show();
        }
    }
    // Checking EditText is empty or not.

    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
            //Toast.makeText(Login.this,"Login Fail",Toast.LENGTH_LONG).show();
        }
        else {
            EditTextEmptyHolder = true ;
            //Toast.makeText(Login.this,"Login bem sucedido",Toast.LENGTH_LONG).show();
        }
    }
    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(Login.this,"Login bem sucedido",Toast.LENGTH_LONG).show();
            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(Login.this, Cliente.class);
            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);
        }
        else {
            Toast.makeText(Login.this,"Nome de usuário ou senha estão incorretos, tente novamente!",Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }

}