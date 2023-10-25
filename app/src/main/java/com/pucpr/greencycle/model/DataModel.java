package com.pucpr.greencycle.model;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private ArrayList<Contact>contacts;
    private ContactDatabase database;
    public void createDatabase(Context context){
        database = new ContactDatabase(context);
        contacts = database.getContactsFromDB();
    }
    public ArrayList<Contact>getContacts(){
        return contacts;
    }

    public Contact getContact(int pos){

        return contacts.get(pos);
    }
    public int getContactSize(){

        return contacts.size();
    }
    public boolean addContact(Contact c){
        long id = database.createContactInDB(c);
        if (id > 0){
            c.setId(id);
            contacts.add(c);
            return true;
        }
        return false;
    }
    public boolean insertContact(Contact c,int pos){
        long id = database.insertContactInDB(c);
        if (id > 0){
            contacts.add(pos,c);
            return true;
        }
        return false;
    }
    public boolean updateContact(Contact c, int pos){
        int count = database.updateContactInDB(c);
        if (count == 1){
            contacts.set(pos,c);
            return true;
        }
        return false;
    }
    public boolean removeContact(int pos){
        int count = database.removeContactInDB(
                getContact(pos)
        );
        if (count == 1){
            contacts.remove(pos);
            return true;
        }
        return false;
    }
    public UserDetails userDetails =
            new UserDetails("roberto", "1234");
    public UserDetails comDetails =
            new UserDetails("vitor", "1234");
    public UserDetails userDetails01 =
            new UserDetails("nathali", "1234");
    public UserDetails comDetails01 =
            new UserDetails("mariela","1234");

}
