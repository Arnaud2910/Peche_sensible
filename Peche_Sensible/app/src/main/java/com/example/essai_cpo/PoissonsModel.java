package com.example.essai_cpo;

import java.io.Serializable;

public class PoissonsModel extends Animal {

    private String taille_mini;
    private String marquage;

    public PoissonsModel(String name, String scientific_name,int images,String taille_mini, String marquage) {
        super(name,scientific_name,images);
        this.taille_mini=taille_mini;
        this.marquage=marquage;
    }


    public String getTaille_mini() {
        return taille_mini;
    }

    public void setTaille_mini(String taille_mini) {
        this.taille_mini = taille_mini;
    }

    public String getMarquage() {
        return marquage;
    }

    public void setMarquage(String marquage) {
        this.marquage = marquage;
    }
}