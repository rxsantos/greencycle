package com.pucpr.greencycle.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pucpr.greencycle.R;

import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;




public class FormCliente extends AppCompatActivity {

    RadioButton collector, delivery;
    TextView editTextEmailCl, editTextNameCl;
    int index;
    String idlogin;
    EditText

            editTextCpf,
            editTextPhoneCl,
            editTextUfCl,
            editTextCityCl,
            editTextAddressCl,
            editTextPostalCl,
            editTextCountryCl,
            editTextResiduoCl;

ContactDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);
        setTitle("Activity Formulário Cliente");




        editTextNameCl = findViewById(R.id.editTextNameCl);
        editTextEmailCl = findViewById(R.id.editTextEmailCl);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextPhoneCl = findViewById(R.id.editTextPhoneCl);
        editTextUfCl = findViewById(R.id.editTextUfCl);
        editTextCityCl = findViewById(R.id.editTextCityCl);
        editTextAddressCl = findViewById(R.id.editTextAddressCl);
        editTextPostalCl = findViewById(R.id.editTextPostalCl);
        editTextCountryCl = findViewById(R.id.editTextCountryCl);
        editTextResiduoCl = findViewById(R.id.editTextResiduoCl);

        database = new ContactDatabase(this);
        DataModel.getInstance().createDatabase(FormCliente.this);
        //DataModel.getInstance().createClientDatabase(FormCliente.this);



        Bundle extras = getIntent().getExtras();
        int index = Integer.parseInt(extras.getString("EXTRA_USERID"));
        idlogin = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        String Email = extras.getString("EXTRA_EMAIL");
        System.out.println(index);
            editTextNameCl.setText(Name);
            editTextEmailCl.setText(Email);



    }
    public void EmptyEditTexBeforeBack(){
        //editTextNameCl.getText().clear();
        //editTextEmailCl.getText().clear();
    }

    public void formClienteButtonOnClick(View v){
        String Nome = editTextNameCl.getText().toString();
        String Email = editTextEmailCl.getText().toString();
        String Cpf = editTextCpf.getText().toString();
        String Phone = editTextPhoneCl.getText().toString();
        String Estado = editTextUfCl.getText().toString();
        String Cidade = editTextCityCl.getText().toString();
        String Endereco = editTextAddressCl.getText().toString();
        String Cep = editTextPostalCl.getText().toString();
        String Pais = editTextCountryCl.getText().toString();
        String TipoResiduo = editTextResiduoCl.getText().toString();


        if (Nome.length() > 1 && Email.length() > 1 && Cpf.length() > 1) {

            /*Contact c = DataModel.getInstance().getContact(index);
            c.setId_login(id_login);
            c.setName(Nome);
            c.setEmail(Email);
            c.setCpf(Cpf);
            c.setPhone(Phone);
            c.setState(Estado);
            c.setCity(Cidade);
            c.setAddress(Endereco);
            c.setZipcode(Cep);
            c.setCountry(Pais);
            c.setResiduo(TipoResiduo);*/


            //DataModel.getInstance().addContact(
              //      new Contact(id_login, Nome, Email,Cpf,Phone,Estado,Cidade,Endereco,Cep,Pais,TipoResiduo));
           database.createContactInDB(new Contact(idlogin, Nome, Email,Cpf,Phone,Estado,Cidade,Endereco,Cep,Pais,TipoResiduo));
            Toast.makeText(this, "Usuário Cadastrado com Sucesso!"+ idlogin, Toast.LENGTH_SHORT).show();

            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder( FormCliente.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.empty_contact_alert_msg);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();

        }

    }


    /*
    public void onBackPressed(){
        //EmptyEditTexBeforeBack();
        finish();
    }*/


}