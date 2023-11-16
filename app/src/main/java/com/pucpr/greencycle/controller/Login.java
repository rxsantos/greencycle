package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.ContactDatabase;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button Login, Cadastrar;
    EditText Email, Password;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    String TempPassword = "Usuário não Encontrado", op = "Admin", Name, Ident;
    public static final String UserEmail = "";
    public static final String UserName = "";
    public static final String UserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Activity Login");
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Login = (Button) findViewById(R.id.loginButton);
        Cadastrar = (Button) findViewById(R.id.cadastrarButton);


        database = new ContactDatabase(this);

        //database.createContactInDB(new Contact("Roberto Xavier","roberto@gmail.com", "1234", "Admin"));
        database.insertContactInDB(new com.pucpr.greencycle.model.Contact("Administrador", "admin@admin.com", "admin", "Admin"));


        /*ArrayList<com.pucpr.greencycle.model.Contact>contacts = database.getContactsFromDB();
        for (com.pucpr.greencycle.model.Contact c:contacts) {
            c.print();
        }*/
        //contacts.get(6).setOp("Empresa");
        //database.updateContactInDB(contacts.get(6));
        //database.removeContactInDB(contacts.get(4));
        //database.removeContactInDB(contacts.get(1));
        //database.removeContactInDB(contacts.get(2));
        //database.removeContactInDB(contacts.get(4));
        //database.removeContactInDB(contacts.get(5));
        //database.removeContactInDB(contacts.get(0));
        //database.removeContactInDB(contacts.get(0));
        //contacts.get(53).setName("Roberto Santos");



    }
    public void loginButtonOnClick(View v){


        // Calling EditText is empty or no method.
        CheckEditTextStatus();

        // Calling login method.
        LoginFunction();

    }


    public void registerButtonOnClick(View v){
        Intent intent = new Intent(Login.this, Registro.class);
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
                    op = cursor.getString(cursor.getColumnIndexOrThrow("op"));
                    Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Ident = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    //System.out.println(Ident);


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
            //Toast.makeText(Login.this,"Login bem sucedido!",Toast.LENGTH_LONG).show();
        }
    }
    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            if(op.equals("Cliente")){
                Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_SHORT).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, ApresentacaoCliente.class);
                // Sending Email to Dashboard Activity using intent.
                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME",Name);
                extras.putString("EXTRA_EMAIL",EmailHolder);
                extras.putString("EXTRA_USERID",Ident);
                intent.putExtras(extras);
                startActivity(intent);

            }else if(op.equals("Empresa")){
                Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_SHORT).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, ApresentacaoEmpresa.class);
                // Sending Email to Dashboard Activity using intent.
                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME",Name);
                extras.putString("EXTRA_EMAIL",EmailHolder);
                extras.putString("EXTRA_USERID",Ident);
                intent.putExtras(extras);
                startActivity(intent);
            }else{
                //Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_LONG).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, Contact.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra(UserEmail, EmailHolder);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(Login.this,"Nome de usuário ou senha estão incorretos, tente novamente!",Toast.LENGTH_SHORT).show();
            System.out.println(TempPassword);
        }
        TempPassword = "NOT_FOUND" ;
    }

}