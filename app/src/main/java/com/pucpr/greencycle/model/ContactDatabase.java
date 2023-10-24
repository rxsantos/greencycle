package com.pucpr.greencycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Contact";
    private static final String COL_ID = "id";
    private static final String COL_OP = "op";
    private static final String COL_NAME =  "name";
    private static final String COL_PHONE = "phone";
    private static final String COL_WHATSAPP = "whatsapp";
    private static final String COL_ADDRESS = "address";
    private static final String COL_CITY = "address";
    private static final String COL_ZIPCODE = "address";
    private static final String COL_COUNTRY = "address";
    private static final String COL_EMAIL = "email";
    private static final String COL_CPF = "cpf";
    private static final String COL_CNPJ = "cnpj";
    private static final String COL_RESIDUO = "residuo";
    private static final String COL_DATA = "data";
    private static final String COL_HORA = "hora";

    public ContactDatabase(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "Create table if not exists " +DB_TABLE + "("+
                COL_ID + " integer primary key autoincrement, "+
                COL_OP + " integer, "+
                COL_NAME + " text, "+
                COL_PHONE + " text, "+
                COL_WHATSAPP + " text, "+
                COL_ADDRESS + " text, "+
                COL_CITY + " text, "+
                COL_ZIPCODE + " text, "+
                COL_COUNTRY + " text, "+
                COL_EMAIL + " text, "+
                COL_CPF + " text, "+
                COL_CNPJ + " text, "+
                COL_RESIDUO + " text, "+
                COL_DATA + " text, "+
                COL_HORA + " text)";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long createContactInDB(Contact c){
        ContentValues values = new ContentValues();
        values.put(COL_OP,c.getOp());
        values.put(COL_NAME,c.getName());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_WHATSAPP,c.getWhatsapp());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_CITY,c.getCity());
        values.put(COL_ZIPCODE,c.getZipcode());
        values.put(COL_COUNTRY,c.getCountry());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_CNPJ,c.getCnpj());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_DATA,c.getData());
        values.put(COL_HORA,c.getHora());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }
    public long insertContactInDB(Contact c){
        ContentValues values = new ContentValues();
        values.put(COL_ID,c.getId());
        values.put(COL_OP,c.getOp());
        values.put(COL_NAME,c.getName());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_WHATSAPP,c.getWhatsapp());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_CITY,c.getCity());
        values.put(COL_ZIPCODE,c.getZipcode());
        values.put(COL_COUNTRY,c.getCountry());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_CNPJ,c.getCnpj());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_DATA,c.getData());
        values.put(COL_HORA,c.getHora());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }
    public ArrayList<Contact> getContactsFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<Contact>contacts = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String op = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_OP));
                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE));
                String whatsapp = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_WHATSAPP));
                String address = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String city = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CITY));
                String zipcode = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZIPCODE));
                String country = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_COUNTRY));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cpf = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CPF));
                String cnpj = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CNPJ));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String data = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATA));
                String hora = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_HORA));
                contacts.add(new Contact(id, op, name, phone, whatsapp, address, email,cpf, cnpj,residuo,data,hora));
            }while (cursor.moveToNext());
        }
        database.close();
        return contacts;
    }
    public int updateContactInDB(Contact c){
        ContentValues values = new ContentValues();
        values.put(COL_OP,c.getOp());
        values.put(COL_NAME,c.getName());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_WHATSAPP,c.getWhatsapp());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_CNPJ,c.getCnpj());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_DATA,c.getData());
        values.put(COL_HORA,c.getHora());
        String id = String.valueOf(c.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeContactInDB(Contact c){
        String id = String.valueOf(c.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }


}
