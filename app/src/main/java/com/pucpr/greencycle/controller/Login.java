package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.DataModel;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Activity Login");
    }
    public void loginButtonOnClick(View v){
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(username.equals(DataModel.getInstance().userDetails.getUsername()) &&
                password.equals(DataModel.getInstance().userDetails.getPassword())){
            /*
            Toast.makeText(MainActivity.this,
                    getString(R.string.user_logged),
                    Toast.LENGTH_LONG).show();*/
            Intent intent = new Intent(Login.this, Cliente.class);
            startActivity(intent);
        }else if (username.equals(DataModel.getInstance().comDetails.getUsername()) &&
                password.equals(DataModel.getInstance().comDetails.getPassword())){
            Intent intent = new Intent(Login.this, Empresa.class);
            startActivity(intent);

        }else if (username.equals(DataModel.getInstance().userDetails01.getUsername()) &&
                password.equals(DataModel.getInstance().userDetails01.getPassword())){
            Intent intent = new Intent(Login.this, Cliente.class);
            startActivity(intent);

        }else if(username.equals(DataModel.getInstance().comDetails01.getUsername()) &&
                password.equals(DataModel.getInstance().comDetails01.getPassword())){
            Intent intent = new Intent(Login.this, Empresa.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(getString(R.string.wrong_user));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    usernameEditText.setText("");
                    passwordEditText.setText("");

                }
            });

            builder.create().show();
        }
    }
    public void registerButtonOnClick(View v){
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }
    public void recoverButtonOnClick(View v){
        Intent intent = new Intent(Login.this, RecuperarSenha.class);
        startActivity(intent);
    }

}