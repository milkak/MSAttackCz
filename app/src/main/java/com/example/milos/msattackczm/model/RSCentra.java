package com.example.milos.msattackczm.model;

import java.util.Date;

/**
 * Created by milos on 24.3.2015.
 */
public class RSCentra {

    private String nazev;
    private String adresa;
    private String url;
    private String email;



    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return nazev+ adresa;
    }



}
