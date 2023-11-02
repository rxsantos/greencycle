package com.pucpr.greencycle.model;

public class UserDetails {

    private long id;
    private String name, email, cpf, cnpj, phone, state,  city, address, zipcode, country, residuo, region;

    public UserDetails(String name, String email, String cpf, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String region) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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

    public UserDetails(long id, String name, String email, String cpf, String cnpj, String phone, String state, String city, String address, String zipcode, String country, String residuo, String region) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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
}
