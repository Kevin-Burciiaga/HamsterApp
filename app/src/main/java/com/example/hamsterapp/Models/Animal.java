package com.example.hamsterapp.Models;

public class Animal {
    int id;
    String Nombre;

public Animal( String nombre, int id) {
        this.id = id;
        this.Nombre = nombre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}
