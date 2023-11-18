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
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;

public class FormPedidos extends AppCompatActivity {
    FormPedidosAdapter adapter = new FormPedidosAdapter();
    Cursor cursor;
    String NameHolder, EmailHolder, IdentHolder="",idlogin, CnpjHolder, PhoneHolder, EstadoHolder,
            CidadeHolder, EnderecoHolder, CepHolder, PaisHolder, TipoResiduoHolder, DescResiduoHolder, RegionHolder;
    int IdHolder;
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
                Company c = DataModel.getInstance().getCompany(position);



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
        CheckingDataCompany();
        if(adapter.getSelected() != null){
            Toast.makeText(this, "Seu pendido foi enviado para "+adapter.getSelected(), Toast.LENGTH_SHORT).show();
            //finish();
        }else {
            Toast.makeText(this, "Selecione uma empresa.", Toast.LENGTH_SHORT).show();
        }

    }

    public void CheckingDataCompany(){
        if (adapter.getSelected() != null){
        EmailHolder = adapter.getSelected();
        // Abrindo SQLite database com permissoes de leitura.
        sqLiteDatabaseObj = database.getReadableDatabase();
        //System.out.println(EmailHolder);
        // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Empresa",null, ""+"email"+"=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // Storing Password associated with entered email.
                IdHolder = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                idlogin = cursor.getString(cursor.getColumnIndexOrThrow("company_idlogin"));
                NameHolder = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                EmailHolder = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                CnpjHolder = cursor.getString(cursor.getColumnIndexOrThrow("cnpj"));
                PhoneHolder = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                EstadoHolder = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                CidadeHolder = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                EnderecoHolder = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                CepHolder = cursor.getString(cursor.getColumnIndexOrThrow("zipcode"));
                PaisHolder = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                TipoResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                DescResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));
                RegionHolder = cursor.getString(cursor.getColumnIndexOrThrow("region"));

                //Fechando cursor.
                cursor.close();
                }
            }
        }

    }
}