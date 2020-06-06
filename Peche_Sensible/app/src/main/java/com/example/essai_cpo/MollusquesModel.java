package com.example.essai_cpo;
import java.io.Serializable;

public class MollusquesModel extends Animal {


    private String tailles_minis;
    private String quantité_maxi;

    public MollusquesModel(String name, String scientific_name, int images, String tailles_minis, String quantité_maxi) {
        super(name,scientific_name,images);
        this.tailles_minis = tailles_minis;
        this.quantité_maxi = quantité_maxi;
    }


    public String getTailles_minis() {
        return tailles_minis;
    }

    public void setTailles_minis(String tailles_minis) {
        this.tailles_minis = tailles_minis;
    }

    public String getQuantité_maxi() {
        return quantité_maxi;
    }

    public void setQuantité_maxi(String quantité_maxi) {
        this.quantité_maxi = quantité_maxi;
    }
}
