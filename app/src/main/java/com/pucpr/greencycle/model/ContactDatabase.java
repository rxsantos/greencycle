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
    private static final String COL_NAME =  "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_OP = "op";

    private Context context;
    public ContactDatabase(Context context){

        super(context, DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "Create Table if not exists "+DB_TABLE + "( "+
                COL_ID + " Integer primary key autoincrement, "+
                COL_NAME + " TEXT, "+
                COL_EMAIL + " TEXT, "+
                COL_PASSWORD + " TEXT, "+
                COL_OP + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public long createContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_OP,c.getOp());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }
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
    public ArrayList<Contact> getContactsFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<Contact>contacts = new ArrayList<>();
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
                contacts.add(new Contact(id, name, email, password, op));
            }while (cursor.moveToNext());
        }
        database.close();
        return contacts;
    }
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
