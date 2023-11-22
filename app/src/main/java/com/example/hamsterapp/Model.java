package com.example.hamsterapp;

public class Model {

    int image;
    String info;

    public Model(int image, String info) {

        this.image=image;
        this.info=info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
