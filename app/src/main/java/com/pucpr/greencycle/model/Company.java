package com.pucpr.greencycle.model;


import android.util.Log;

public class Company {

    private long id;

    private String idlogin, name, email, cnpj, phone, state,  city, address, zipcode, country,residuo, descresiduo, region;

    public Company(String idlogin, String name, String email, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String descresiduo, String region) {
        this.idlogin = idlogin;
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
        this.descresiduo = descresiduo;
        this.region = region;
    }

    public Company(long id, String idlogin, String name, String email, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String descresiduo,String region) {
        this.id = id;
        this.idlogin = idlogin;
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
        this.descresiduo = descresiduo;
        this.region = region;
    }

    public String getDescresiduo() {
        return descresiduo;
    }

    public void setDescresiduo(String descresiduo) {
        this.descresiduo = descresiduo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(String idlogin) {
        this.idlogin = idlogin;
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
    public void print(){
        Log.v("SQLDatabase", "Contact["+id+"]:" + " Idlogin: " +idlogin+ " Nome: " +name+" Email: "+email+ " CPF: "+cnpj+ " Cidade: " +city+ " Estado: " +state+
                " Endereço: " +address+ " Residio: " + residuo+ " DescResiduo: "+descresiduo+" Regiao: " +region);
    }
}
