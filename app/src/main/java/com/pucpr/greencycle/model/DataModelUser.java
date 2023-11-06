package com.pucpr.greencycle.model;

import android.content.Context;

import java.util.ArrayList;

public class DataModelUser {

    private static DataModelUser instance = new DataModelUser();
    private DataModelUser(){

    }
    public static DataModelUser getInstance(){
        return instance;
    }
    private ArrayList<UserDetails> userdetails;
    private UserDetailsDatabase database;
    public void createUserDatabase(Context context){
        database = new UserDetailsDatabase(context);
        userdetails = database.getUserDetailsFromDB();
    }
    public ArrayList<UserDetails>getUserdetails(){
        return userdetails;
    }
    public UserDetails getUserDetail(int pos){
        return userdetails.get(pos);
    }
    public int getUserDetailsSize(){
        return userdetails.size();
    }
    public boolean addUserDetail(UserDetails c){
        long id = database.createUserDetailInDB(c);
        if (id > 0){
            c.setId(id);
            userdetails.add(c);
            return true;
        }
        return false;
    }
    public boolean insertUserDetail(UserDetails c,int pos){
        long id = database.insertUserDetailInDB(c);
        if (id > 0){
            userdetails.add(pos,c);
            return true;
        }
        return false;
    }
    public boolean updateUserDetail(UserDetails c, int pos){
        int count = database.updateUserDetailInDB(c);
        if (count == 1){
            userdetails.set(pos,c);
            return true;
        }
        return false;
    }
    public boolean removeUserDetail(int pos){
        int count = database.removeUserDetailInDB(
                getUserDetail(pos)
        );
        if (count ==1){
            userdetails.remove(pos);
            return true;
        }
        return false;
    }

}
