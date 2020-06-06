package com.example.essai_cpo;

import java.io.Serializable;

public class CrustacesModel extends Animal {


    private String tailles_minis;
    private String autres_infos;

    public CrustacesModel(String name, String scientific_name, int images, String tailles_minis, String autres_infos) {
        super(name,scientific_name,images);
        this.tailles_minis = tailles_minis;
        this.autres_infos = autres_infos;
    }



    public String getNmb_maxi() {
        return tailles_minis;
    }

    public void setNmb_maxi(String tailles_minis) {
        this.tailles_minis = tailles_minis;
    }

    public String getAutres_infos() {
        return autres_infos;
    }

    public void setAutres_infos(String autres_infos) {
        this.autres_infos = autres_infos;
    }
}