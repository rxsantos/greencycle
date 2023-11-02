package com.pucpr.greencycle.model;

import android.util.Log;

public class Contact {
    private long id;

    private String name, email, password, op;

    public Contact(long id, String name, String email, String password, String op) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
    }

    public Contact(String name, String email, String password,String op) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void print(){
        Log.v("SQLDatabase", "Contact["+id+"]:"+name+" Email: "+email+ " Password: "+password+ " Tipo: " +op);
    }


}
