package com.example.essai_cpo;

import java.io.Serializable;

public class SessionsModel implements Serializable {

    private String date;
    private String lieu;
    private String contenu;


    public SessionsModel(String date, String lieu,String contenu) {
        this.date = date;
        this.lieu = lieu;
        this.contenu = contenu;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getlieu() {
        return lieu;
    }

    public void setlieu(String lieu) {
        this.lieu = lieu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
