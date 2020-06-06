package com.example.essai_cpo;

import java.io.Serializable;

public abstract class Animal implements Serializable {

    private String name;
    private String scientific_name;
    private int images;

    public Animal(String name, String scientific_name, int images) {
        this.name = name;
        this.scientific_name = scientific_name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
