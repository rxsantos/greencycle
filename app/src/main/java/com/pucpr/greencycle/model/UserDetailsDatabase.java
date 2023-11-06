package com.pucpr.greencycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDetailsDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "userdetails.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "UserDetail";

    private static final String COL_ID = "id";
    private static final String COL_NAME =  "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_CPF = "cpf";
    private static final String COL_CNPJ = "cnpj";
    private static final String COL_PHONE = "phone";
    private static final String COL_STATE = "state";
    private static final String COL_CITY = "city";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIPCODE = "zipcode";
    private static final String COL_COUNTRY = "country";
    private static final String COL_RESIDUO = "residuo";
    private static final String COL_REGION = "region";

    private Context context;
    public UserDetailsDatabase(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "Create Table if not exists "+DB_TABLE + "( "+
                COL_ID + " Integer primary key autoincrement, "+
                COL_NAME + " TEXT, "+
                COL_EMAIL + " TEXT, "+
                COL_CPF + " TEXT, "+
                COL_CNPJ + " TEXT, "+
                COL_PHONE + " TEXT, "+
                COL_STATE + " TEXT, "+
                COL_CITY + " TEXT, "+
                COL_ADDRESS + " TEXT, "+
                COL_ZIPCODE + " TEXT, "+
                COL_COUNTRY + " TEXT, "+
                COL_RESIDUO + " TEXT, "+
                COL_REGION + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public long createUserDetailInDB(UserDetails c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_STATE,c.getState());
        values.put(COL_CITY,c.getCity());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_ZIPCODE,c.getZipcode());
        values.put(COL_COUNTRY,c.getCountry());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_REGION,c.getRegion());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }
    public long insertUserDetailInDB(UserDetails c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,c.getId());
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_STATE,c.getState());
        values.put(COL_CITY,c.getCity());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_ZIPCODE,c.getZipcode());
        values.put(COL_COUNTRY,c.getCountry());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_REGION,c.getRegion());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }

    public ArrayList<UserDetails> getUserDetailsFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<UserDetails>userdetails = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cpf = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CPF));
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
                userdetails.add(new UserDetails(id, name, email, cpf, cnpj, phone, state,  city, address, zipcode, country, residuo, region));
            }while (cursor.moveToNext());
        }
        database.close();
        return userdetails;
    }

    public int updateUserDetailInDB(UserDetails c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_CPF,c.getCpf());
        values.put(COL_PHONE,c.getPhone());
        values.put(COL_STATE,c.getState());
        values.put(COL_CITY,c.getCity());
        values.put(COL_ADDRESS,c.getAddress());
        values.put(COL_ZIPCODE,c.getZipcode());
        values.put(COL_COUNTRY,c.getCountry());
        values.put(COL_RESIDUO,c.getResiduo());
        values.put(COL_REGION,c.getRegion());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE, values,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeUserDetailInDB(UserDetails c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
}
