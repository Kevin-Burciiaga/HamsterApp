package com.example.hamsterapp.Models;

public class Animal {
    int image;
    String nombre;

public Animal(int image, String nombre) {
        this.image = image;
        this.nombre = nombre;
    }

public int getImage() {
        return image;
    }

public void setImage(int image) {
        this.image = image;
    }

public String getNombre() {
        return nombre;
    }

public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
