package com.pucpr.greencycle.controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.DataModel;


public class UpdateUsers extends AppCompatActivity {

    EditText editNameUser, editEmailUser, editPasswordUser;

    RadioButton Cliente, Empresa, Admin;

    int index;
    String Op;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_users);
        setTitle("Activity Adicionar Usuários");

        DataModel.getInstance().createDatabase(UpdateUsers.this);



        editNameUser = findViewById(R.id.editNameUpdate);
        editEmailUser = findViewById(R.id.editEmailUpdate);
        editPasswordUser = findViewById(R.id.editPasswordUpdate);
        Cliente = (RadioButton) findViewById(R.id.radioButtonClienteUp);
        Empresa  = (RadioButton) findViewById(R.id.radioButtonEmpresaUp);
        Admin  = (RadioButton) findViewById(R.id.radioButtonAdminUp);

        Bundle extra = getIntent().getExtras();
        index = extra.getInt("index");
        if (index != -1){
            Contact c = DataModel.getInstance().getContact(index);
            editNameUser.setText(c.getName());
            editEmailUser.setText(c.getEmail());
            editPasswordUser.setText(c.getPassword());
        }


    }
    @SuppressLint("MissingSuperCall")
    public void onBackPressed(){
        String Nome = editNameUser.getText().toString();
        String Email = editEmailUser.getText().toString();
        String Password = editPasswordUser.getText().toString();

        if (Admin.isChecked()){
            Op = "Admin";
        }else if(Empresa.isChecked()){
            Op = "Empresa";
        } else {
            Op = "Cliente";
        }
        if (Nome.length() > 1 && Email.length() > 1 && Password.length() > 1) {
            if (index == -1) {
                DataModel.getInstance().addContact(
                        new Contact(Nome, Email, Password, Op)
                );
            } else {
                Contact c = DataModel.getInstance().getContact(index);
                c.setName(Nome);
                c.setEmail(Email);
                c.setPassword(Password);
                c.setOp(Op);
                DataModel.getInstance().updateContact(c, index);
            }
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder( UpdateUsers.this);
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


}