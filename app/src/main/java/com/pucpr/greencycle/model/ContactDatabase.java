package com.pucpr.greencycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ContactDatabase extends SQLiteOpenHelper {
    //Iniciar Variaveis
    private static final String DB_NAME = "contacts.sqlite";
    private static final int DB_VERSION = 6;
    private static final String DB_TABLE = "Contact";
    private static final String DB_TABLE_CLIENT = "Cliente";
    private static final String DB_TABLE_COMPANY = "Empresa";
    private static final String COL_ID = "id";
    private static final String COL_ID_LOGIN_COMPANY = "company_idlogin";
    private static final String COL_ID_LOGIN_CLIENT = "client_idlogin";
    private static final String COL_NAME =  "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_OP = "op";
    private static final String COL_CPF = "cpf";
    private static final String COL_CNPJ = "cnpj";
    private static final String COL_PHONE = "phone";
    private static final String COL_STATE = "state";
    private static final String COL_CITY = "city";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIPCODE = "zipcode";
    private static final String COL_COUNTRY = "country";
    private static final String COL_RESIDUO = "residuo";
    private static final String COL_DESC_RESIDUO = "descresiduo";
    private static final String COL_REGION = "region";

    //Criar Tabelas
    private static final String CREATE_TABLE_LOGIN = "Create Table if not exists "+DB_TABLE + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_PASSWORD + " TEXT, "+
            COL_OP + " TEXT)";

    private static final String CREATE_TABLE_CLIENT = "Create Table if not exists "+DB_TABLE_CLIENT + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_ID_LOGIN_CLIENT + " TEXT, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_CPF + " TEXT, "+
            COL_PHONE + " TEXT, "+
            COL_STATE + " TEXT, "+
            COL_CITY + " TEXT, "+
            COL_ADDRESS + " TEXT, "+
            COL_ZIPCODE + " TEXT, "+
            COL_COUNTRY + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO + " TEXT)";

    private static final String CREATE_TABLE_COMPANY = "Create Table if not exists "+DB_TABLE_COMPANY + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_ID_LOGIN_COMPANY + " TEXT, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_CNPJ + " TEXT, "+
            COL_PHONE + " TEXT, "+
            COL_STATE + " TEXT, "+
            COL_CITY + " TEXT, "+
            COL_ADDRESS + " TEXT, "+
            COL_ZIPCODE + " TEXT, "+
            COL_COUNTRY + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO + " TEXT, "+
            COL_REGION + " TEXT)";

    private Context context;
    public ContactDatabase(Context context){
        super(context, DB_NAME,null,DB_VERSION);
        Log.d("table", CREATE_TABLE_LOGIN);
        Log.d("table", CREATE_TABLE_CLIENT);
        Log.d("table", CREATE_TABLE_COMPANY);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE_LOGIN);
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE+"'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_CLIENT+"'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_COMPANY+"'");
        onCreate(sqLiteDatabase);
    }

    //Criar Metodo Insert para as Tabelas
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
        database.close();
        return id;
    }

    public long createClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        long id = database.insert(DB_TABLE_CLIENT, null, values_client);
        database.close();
        return id;
    }

    public long createCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
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
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        long id = database.insert(DB_TABLE_COMPANY, null, values_company);
        database.close();
        return id;
    }

    //Criar Metodo Insert com ID para as Tabelas
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

    public long insertClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID, c.getId());
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        long id = database.insert(DB_TABLE_CLIENT, null, values_client);
        database.close();
        return id;
    }

    public long insertCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID, c.getId());
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
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
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        long id = database.insert(DB_TABLE_COMPANY, null, values_company);
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

    public ArrayList<Client> getClientsFromDB(){
        ArrayList<Client>clients = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_CLIENT, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String idlogin = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cpf = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CPF));
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
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                clients.add(
                        new Client(id, idlogin, name, email, cpf, phone, state,  city, address, zipcode, country, residuo, descresiduo));
            }while (cursor.moveToNext());
        }
        database.close();
        return clients;
    }

    public ArrayList<Company> getCompaniesFromDB(){
        ArrayList<Company>companies = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_COMPANY, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String idlogin = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_COMPANY));
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
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                String region = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_REGION));
                companies.add(
                        new Company(id, idlogin, name, email, cnpj, phone, state,  city, address, zipcode, country, residuo, descresiduo, region));
            }while (cursor.moveToNext());
        }
        database.close();
        return companies;
    }

    //Criar Metodo Update para as Tabelas
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

    public int updateClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE_CLIENT, values_client,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int updateCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
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
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE_COMPANY, values_company,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    //Criar Metodo Remove para as Tabelas
    public int removeContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeClientInDB(Client c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE_CLIENT,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeCompanyInDB(Company c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE_COMPANY,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

}
