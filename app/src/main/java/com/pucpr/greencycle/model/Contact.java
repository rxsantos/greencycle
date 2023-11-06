package com.pucpr.greencycle.model;

import android.util.Log;

public class Contact {
    private long id,id_c, id_e;

    private String id_login, name, email, password, op, cpf, cnpj, phone, state, city, address, zipcode, country, residuo,region;


    // Tabela Contato Login
    public Contact(long id, String name, String email, String password, String op) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
    }

    // Tabela Contato Login
    public Contact(String name, String email, String password,String op) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
    }

    //Tabela Client
    public Contact(long id_c, String id_login, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo) {
        this.id_c = id_c;
        this.id_login = id_login;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
    }
    public Contact(String id_login, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo) {
        this.id_login = id_login;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
    }

    //Tabela Company
    public Contact(long id_e, String id_login, String name, String email, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String region) {
        this.id_e = id_e;
        this.id_login = id_login;
        this.name = name;
        this.email = email;
        this.cnpj = cnpj;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.region = region;
    }

    public Contact(String id_login, String name, String email, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String region) {
        this.id_login = id_login;
        this.name = name;
        this.email = email;
        this.cnpj = cnpj;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.region = region;
    }

    public long getId_e() {
        return id_e;
    }

    public void setId_e(long id_e) {
        this.id_e = id_e;
    }

    public long getId_c() {
        return id_c;
    }

    public void setId_c(long id_c) {
        this.id_c = id_c;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResiduo() {
        return residuo;
    }

    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
