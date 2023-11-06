package com.pucpr.greencycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactDatabase extends SQLiteOpenHelper {
    //Iniciar Variaveis
    private static final String DB_NAME = "contacts.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Contact";
    private static final String DB_TABLE_CLIENT = "Client_Table";
    private static final String DB_TABLE_COMPANY = "Company_Table";
    private static final String COL_ID = "id";
    private static final String COL_ID_CLIENT = "id_cliente";
    private static final String COL_ID_COMPANY = "id_company";
    private static final String COL_ID_LOGIN_COMPANY = "company_idlogin";
    private static final String COL_ID_LOGIN_CLIENT = "client_idlogin";
    private static final String COL_NAME =  "name";
    private static final String COL_NAME_CLIENT =  "client_name";
    private static final String COL_NAME_COMPANY =  "company_name";
    private static final String COL_EMAIL = "email";
    private static final String COL_EMAIL_COMPANY = "company_email";
    private static final String COL_EMAIL_CLIENT = "client_email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_OP = "op";
    private static final String COL_CPF = "cpf";
    private static final String COL_CNPJ = "cnpj";
    private static final String COL_PHONE_COMPANY = "company_phone";
    private static final String COL_PHONE_CLIENT = "client_phone";
    private static final String COL_STATE_COMPANY = "company_state";
    private static final String COL_STATE_CLIENT = "client_state";
    private static final String COL_CITY_COMPANY = "company_city";
    private static final String COL_CITY_CLIENT = "client_city";
    private static final String COL_ADDRESS_COMPANY = "company_address";
    private static final String COL_ADDRESS_CLIENT = "client_address";
    private static final String COL_ZIPCODE_COMPANY = "company_zipcode";
    private static final String COL_ZIPCODE_CLIENT = "client_zipcode";
    private static final String COL_COUNTRY_COMPANY = "company_country";
    private static final String COL_COUNTRY_CLIENT = "client_country";
    private static final String COL_RESIDUO_COMPANY = "company_residuo";
    private static final String COL_RESIDUO_CLIENT = "client_residuo";
    private static final String COL_REGION = "region";


    private Context context;
    public ContactDatabase(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //Criar Tabelas
        String table_login = "Create Table if not exists "+DB_TABLE + "( "+
                COL_ID + " Integer primary key autoincrement, "+
                COL_NAME + " TEXT, "+
                COL_EMAIL + " TEXT, "+
                COL_PASSWORD + " TEXT, "+
                COL_OP + " TEXT)";
        String table_client = "Create Table if not exists "+DB_TABLE_CLIENT + "( "+
                COL_ID_CLIENT + " Integer primary key autoincrement, "+
                COL_ID_LOGIN_CLIENT + " TEXT, "+
                COL_NAME_CLIENT + " TEXT, "+
                COL_EMAIL_CLIENT + " TEXT, "+
                COL_CPF + " TEXT, "+
                COL_PHONE_CLIENT + " TEXT, "+
                COL_STATE_CLIENT + " TEXT, "+
                COL_CITY_CLIENT + " TEXT, "+
                COL_ADDRESS_CLIENT + " TEXT, "+
                COL_ZIPCODE_CLIENT + " TEXT, "+
                COL_COUNTRY_CLIENT + " TEXT, "+
                COL_RESIDUO_CLIENT + " TEXT)";
        String table_company = "Create Table if not exists "+DB_TABLE_COMPANY + "( "+
                COL_ID_COMPANY + " Integer primary key autoincrement, "+
                COL_ID_LOGIN_COMPANY + " TEXT, "+
                COL_NAME_COMPANY + " TEXT, "+
                COL_EMAIL_COMPANY + " TEXT, "+
                COL_CNPJ + " TEXT, "+
                COL_PHONE_COMPANY + " TEXT, "+
                COL_STATE_COMPANY + " TEXT, "+
                COL_CITY_COMPANY + " TEXT, "+
                COL_ADDRESS_COMPANY + " TEXT, "+
                COL_ZIPCODE_COMPANY + " TEXT, "+
                COL_COUNTRY_COMPANY + " TEXT, "+
                COL_RESIDUO_COMPANY + " TEXT, "+
                COL_REGION + " TEXT)";

        sqLiteDatabase.execSQL(table_login);
        sqLiteDatabase.execSQL(table_client);
        sqLiteDatabase.execSQL(table_company);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_CLIENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_COMPANY);
        onCreate(sqLiteDatabase);

    }
    //Criar Metodo Inserte para as 3 Tabelas
    public long createContactInDB(Contact c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();

        //Criar ContentValues Tabela Login
        ContentValues values_login = new ContentValues();
        values_login.put(COL_NAME, c.getName());
        values_login.put(COL_EMAIL, c.getEmail());
        values_login.put(COL_PASSWORD, c.getPassword());
        values_login.put(COL_OP, c.getOp());
        long id = database.insert(DB_TABLE, null, values_login);

        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID_LOGIN_CLIENT, c.getId_login());
        values_client.put(COL_NAME_CLIENT, c.getName());
        values_client.put(COL_EMAIL_CLIENT, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE_CLIENT, c.getPhone());
        values_client.put(COL_STATE_CLIENT, c.getState());
        values_client.put(COL_CITY_CLIENT, c.getCity());
        values_client.put(COL_ADDRESS_CLIENT, c.getAddress());
        values_client.put(COL_ZIPCODE_CLIENT, c.getZipcode());
        values_client.put(COL_COUNTRY_CLIENT, c.getCountry());
        values_client.put(COL_RESIDUO_CLIENT, c.getResiduo());
        long id_c = database.insert(DB_TABLE_CLIENT, null, values_client);


//Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID_LOGIN_COMPANY, c.getId_login());
        values_company.put(COL_NAME_COMPANY, c.getName());
        values_company.put(COL_EMAIL_COMPANY, c.getEmail());
        values_company.put(COL_CNPJ, c.getCnpj());
        values_company.put(COL_PHONE_COMPANY, c.getPhone());
        values_company.put(COL_STATE_COMPANY, c.getState());
        values_company.put(COL_CITY_COMPANY, c.getCity());
        values_company.put(COL_ADDRESS_COMPANY, c.getAddress());
        values_company.put(COL_ZIPCODE_COMPANY, c.getZipcode());
        values_company.put(COL_COUNTRY_COMPANY, c.getCountry());
        values_company.put(COL_RESIDUO_COMPANY, c.getResiduo());
        values_company.put(COL_REGION, c.getRegion());
        long id_e = database.insert(DB_TABLE_COMPANY, null, values_company);

        database.close();

        if (id > -1) {
            return id;
        }
        if (id_c > -1) {
            return id_c;
        }
        if (id_e > -1) {
            return id_e;
        }

        return id;
    }

    public long createClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();

        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME_CLIENT, c.getName());
        values_client.put(COL_EMAIL_CLIENT, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE_CLIENT, c.getPhone());
        values_client.put(COL_STATE_CLIENT, c.getState());
        values_client.put(COL_CITY_CLIENT, c.getCity());
        values_client.put(COL_ADDRESS_CLIENT, c.getAddress());
        values_client.put(COL_ZIPCODE_CLIENT, c.getZipcode());
        values_client.put(COL_COUNTRY_CLIENT, c.getCountry());
        values_client.put(COL_RESIDUO_CLIENT, c.getResiduo());
        long id = database.insert(DB_TABLE_CLIENT, null, values_client);
        database.close();
        return id;
    }
    /*
    public long createCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID_LOGIN, c.getIdlogin());
        values_company.put(COL_NAME, c.getName());
        values_company.put(COL_EMAIL, c.getEmail());
        values_company.put(COL_CNPJ, c.getCnpj());
        values_company.put(COL_PHONE, c.getPhone());
        values_company.put(COL_STATE, c.getState());
        values_company.put(COL_CITY, c.getCity());
        values_company.put(COL_ADDRESS, c.getAddress());
        values_company.put(COL_ZIPCODE, c.getZipcode());
        values_company.put(COL_COUNTRY, c.getCountry());
        values_company.put(COL_RESIDUO, c.getResiduo());
        values_company.put(COL_REGION, c.getRegion());
        long id = database.insert(DB_TABLE_COMPANY, null, values_company);
        database.close();
        return id;
    } */
    public long insertContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,c.getId());
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_OP,c.getOp());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }
    //Criar Metodo GetContactsFromDB (Read)
    public ArrayList<Contact> getContactsFromDB(){
        ArrayList<Contact>contacts = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String password = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PASSWORD));
                String op = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_OP));
                contacts.add(
                        new Contact(id, name, email, password, op));
            }while (cursor.moveToNext());
        }
        database.close();
        return contacts;
    }

    public ArrayList<Client> getClientFromDB(){
        ArrayList<Client>clients = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_CLIENT, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID_CLIENT));
                String id_login = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_CLIENT));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_CLIENT));
                String cpf = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CPF));
                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE_CLIENT));
                String state = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_STATE_CLIENT));
                String city = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CITY_CLIENT));
                String address = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ADDRESS_CLIENT));
                String zipcode = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZIPCODE_CLIENT));
                String country = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_COUNTRY_CLIENT));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO_CLIENT));
                clients.add(
                        new Client(id,id_login, name, email, cpf, phone, state,  city, address, zipcode, country, residuo));
            }while (cursor.moveToNext());
        }
        database.close();
        return clients;
    }
/*
    public ArrayList<Company> getCompanyFromDB(){
        ArrayList<Company>companies = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_COMPANY, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String id_login = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cnpj = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CNPJ));
                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE));
                String state = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_STATE));
                String city = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CITY));
                String address = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String zipcode = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZIPCODE));
                String country = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_COUNTRY));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String region = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_REGION));
                companies.add(
                        new Company(id,id_login, name, email, cnpj, phone, state,  city, address, zipcode, country, residuo, region));
            }while (cursor.moveToNext());
        }
        database.close();
        return companies;
    } */
    public int updateContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_OP,c.getOp());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE, values,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }


}
