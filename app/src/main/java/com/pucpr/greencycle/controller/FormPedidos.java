package com.pucpr.greencycle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;
import com.pucpr.greencycle.model.Request;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class FormPedidos extends AppCompatActivity {
    FormPedidosAdapter adapter = new FormPedidosAdapter();
    Cursor cursor;
    String NameCompany, EmailCompany, IdentCompany="",idlogincompany, CnpjCompany, PhoneCompany, EstadoCompany,
            CidadeCompany, EnderecoCompany, CepCompany, PaisCompany, TipoResiduoCompany, DescResiduoCompany, RegionCompany,EmailHolderCompany;
    String NameClient, EmailClient, IdentClient="",idloginclient, CnpjClient, PhoneClient, EstadoClient,
            CidadeClient, EnderecoClient, CepClient, PaisClient, TipoResiduoClient, DescResiduoClient, RegionClient, EmailHolderClient;
    String CompanyContact="null", date="null", hour="null";
    String F_Result = "Usuário não Encontrado", F_Result_Company = "Usuário não Encontrado", F_Result_Client="Usuário não Encontrado";
    int IndexCompany, IndexClient;
    RecyclerView recyclerViewPedidos;
    int singleitem_selection_position = -1;

    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_formpedidos);
        setTitle("Activity Solicitação de Coletas");
        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        database = new ContactDatabase(this);
        DataModel.getInstance().createCompanyDatabase(FormPedidos.this);
        //DataModel.getInstance().createRequestDatabase(FormPedidos.this);

        ArrayList<Request> requests = database.getRequestsFromDB();
        for (Request c:requests) {
            c.print();
        }
        Bundle extras = getIntent().getExtras();
        IndexClient = (extras.getInt("EXTRA_ID"));
        idloginclient = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        EmailHolderClient = extras.getString("EXTRA_EMAIL");

        recyclerViewPedidos.setAdapter(adapter);
        recyclerViewPedidos.setLayoutManager(
                new LinearLayoutManager(FormPedidos.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                FormPedidos.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewPedidos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPedidos.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new FormPedidosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //adapter.notifyItemChanged(position);
                setSingleSelection(position);
                //Company c = DataModel.getInstance().getCompany(position);



            }
        });

    }
    private void setSingleSelection(int adapterPosition){
        if (adapterPosition == RecyclerView.NO_POSITION) {
            return;
        }
        adapter.notifyItemChanged(singleitem_selection_position);
        singleitem_selection_position = adapterPosition;
        adapter.notifyItemChanged(singleitem_selection_position);
    }
    public void enviaSolicitacao(View v){

        if(adapter.getSelected() != null){
            CheckingDataCompany();
            CheckingDataClient();
            CheckingRequest();
            EmptyEditTextAfterDataInsert();
            //Toast.makeText(this, "Seu pendido foi enviado para "+adapter.getSelected(), Toast.LENGTH_SHORT).show();
            //finish();
        }else {
            Toast.makeText(this, "Selecione uma empresa.", Toast.LENGTH_SHORT).show();
        }

    }

    public void CheckingDataCompany(){
        if (adapter.getSelected() != null){
        EmailHolderCompany = adapter.getSelected();
            //System.out.println(EmailCompany);
        // Abrindo SQLite database com permissoes de leitura.
        sqLiteDatabaseObj = database.getReadableDatabase();
        //System.out.println(EmailCompany);
        // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Empresa",null, ""+"email"+"=?",new String[]{EmailHolderCompany}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // Storing Password associated with entered email.
                IndexCompany = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                idlogincompany = cursor.getString(cursor.getColumnIndexOrThrow("company_idlogin"));
                NameCompany = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                EmailCompany = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                //System.out.println(EmailCompany);
                CnpjCompany = cursor.getString(cursor.getColumnIndexOrThrow("cnpj"));
                PhoneCompany = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                EstadoCompany = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                CidadeCompany = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                EnderecoCompany = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                CepCompany = cursor.getString(cursor.getColumnIndexOrThrow("zipcode"));
                PaisCompany = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                TipoResiduoCompany = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                //System.out.println(DescResiduoCompany);
                DescResiduoCompany = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));
                RegionCompany = cursor.getString(cursor.getColumnIndexOrThrow("region"));
                //System.out.println(RegionCompany);

                //Fechando cursor.
                cursor.close();
                }
            }
        }

    }
    public void CheckingDataClient(){
            //EmailClient = EmailHolder;
            // Abrindo SQLite database com permissoes de leitura.
            sqLiteDatabaseObj = database.getReadableDatabase();
            //System.out.println(EmailHolder);
            // Adicionando query de consulta de email para o "cursor".
            cursor = sqLiteDatabaseObj.query("Cliente",null, ""+"email"+"=?",new String[]{EmailHolderClient}, null, null, null);
            while (cursor.moveToNext()){
                if (cursor.isFirst()){
                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    IndexClient = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    idloginclient = cursor.getString(cursor.getColumnIndexOrThrow("client_idlogin"));
                    NameClient = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    EmailClient = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    //System.out.println(EmailClient);
                    CnpjClient = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                    PhoneClient = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                    EstadoClient = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                    CidadeClient = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                    EnderecoClient = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                    CepClient = cursor.getString(cursor.getColumnIndexOrThrow("zipcode"));
                    //System.out.println(CepClient);
                    PaisClient = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                    TipoResiduoClient = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                    DescResiduoClient = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));
                    //System.out.println(DescResiduoClient);

                    //Fechando cursor.
                    cursor.close();
                }
            }
    }
    public void InsertData(){
        if (EmailHolderCompany != null) {
            //int IdClient = Integer.valueOf(IndexClient);
            //int IdCompany = Integer.valueOf(IndexCompany);
            String IdClient = String.valueOf(IndexClient);
            String IdCompany = String.valueOf(IndexCompany);
            database.createRequestInDB(new Request(IdClient, NameClient, EmailClient, IdCompany, NameCompany, EmailCompany, CompanyContact, date, hour, TipoResiduoClient, DescResiduoClient));
            database.close();
            Toast.makeText(this, "Solicitação Cadastrada com Sucesso! ", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Seu pendido foi enviado para "+adapter.getSelected(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            //Printando mensagem toast se  algum EditText do formulario estiver em branco.
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                /*
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

            */
        }
    }
    public void CheckingRequest(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = database.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query("Solicitacao",null,"" + "company_email" + "=?" + " and " + "client_email" + "=?",new String[]{EmailCompany, EmailClient}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result_Company = "Email Encontrado";

                //Closing cursor.
                //cursor.close();
            }
        }

        CheckFinalResult();
    }
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if (F_Result_Company.equalsIgnoreCase("Email Encontrado")){
            // If email is exists then toast msg will display.
            Toast.makeText(FormPedidos.this,"Solicitação já cadastrada para esta empresa, escolha outra.",Toast.LENGTH_SHORT).show();
            //finish();
        }else{
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertData();
            //userButtonOnClick();
        }
        F_Result = "Não Encontrado";
    }
    public void EmptyEditTextAfterDataInsert(){
        F_Result_Company="";

    }
}