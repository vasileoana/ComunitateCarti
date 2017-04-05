package com.proiect.cornel.comunitatecarti.Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by corne on 04.12.2016.
 */

public class User {
    private int Id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private String email;
    private String telefon;
    private String sex;
    private Date dataNasterii;

    public User(int id, String nume, String prenume, String username, String parola, String email, String telefon, String sex, Date dataNasterii) {
        Id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.email = email;
        this.telefon = telefon;
        this.sex = sex;
        this.dataNasterii = dataNasterii;
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", sex='" + sex + '\'' +
                ", dataNasterii=" + dataNasterii +
                '}';
    }
}
