package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.pucpr.greencycle.model.ContactDatabase;

public class Registro extends AppCompatActivity {

    EditText Name, Email, Password;
    Button Cadastro;
    RadioButton Coleta, Descarte;
    String NameHolder, EmailHolder, PasswordHolder, Op;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    ContactDatabase database;
    Cursor cursor;
    String F_Result = "Usuário não Encontrado";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Activity Registro");

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



    //Metodo construtor do SQLite database
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase("contacts.sqlite",Context.MODE_PRIVATE, null);

    }

    //Metodo construtor da tabela SQLite
    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("Create Table if not exists "+ "Contact" + "( "+
                "id" + " Integer primary key autoincrement, "+
                "name" + " TEXT, "+
                "email" + " TEXT, "+
                "password" + " TEXT, "+
                "op" + " TEXT);");

    }

    //Método para inserir dados no Banco de Dados SQLite
    public void InsertDataIntoSQLiteDatabase() {
        if (EditTextEmptyHolder) {
            //Query SQLite para inserir dados nas tabelas
            SQLiteDataBaseQueryHolder = "INSERT INTO " + "Contact" + " (name,email,password,op) VALUES ('" + NameHolder + "','" + EmailHolder + "','" + PasswordHolder + "','" + Op + "');";

            //Executando query
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            //Fechando Objeto SQLite database
            sqLiteDatabaseObj.close();

            //Printando mensagem toast depois de completar o inserte
            //Toast.makeText(Registro.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
        // Este bloco será executado se algum EditText de registro estiver em branco.
        else {

            //Printando mensagem toast se  algum EditText de registro estiver em branco.
            Toast.makeText(Registro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        }
    }

    //Limpa editText depois de finalizar o processo do metodo inserte.
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    //Metodo para checar se EditText está vazio ou não.
    public void CheckEditTextStatus(){

        //Obtendo valor de todos EditText e armazenando em variáveis de String.
        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if (Coleta.isChecked()){
            Op = "Empresa";
        }else if(Descarte.isChecked()){
            Op = "Cliente";
        }

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(Op)){
            EditTextEmptyHolder = false;
        } else{
            EditTextEmptyHolder = true;
        }

    }
        // Verificando se e-mail já existe ou não.
    public void CheckingEmailAlreadyExistsOrNot(){
            // Abrindo SQLite database com permissoes de escrita.
        sqLiteDatabaseObj = database.getWritableDatabase();

            // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Contact",null,"" + "email" + "=?",new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()){
                if (cursor.isFirst()){
                    cursor.moveToFirst();

                    // Se o email já existir então a variável Result será setada com o valor "Email Encontrado".
                    F_Result = "Email Encontrado";

                    //Fechando cursor.
                    cursor.close();
                }
            }
            CheckFinalResult();
        }


        public void CheckFinalResult(){
            // Verificando se email já existe ou não.
        if (F_Result.equalsIgnoreCase("Email Encontrado")){

            // Se email já existir então mensagem Toast será mostrada.
            Toast.makeText(Registro.this,"Email já foi utilizado. Tente outro email!",Toast.LENGTH_LONG).show();
        }else{
            // Se o e-mail não existir, as informacoes de registro do usuário serão inseridas no SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
            F_Result = "Não Encontrado";
        }

}



