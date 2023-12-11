package com.example.hamsterapp.Models;

public class Jaula {

    int id;
    String name;
    int id_user;
    int id_animal;

    public Jaula(int id,String name, int user_id, int animal_id) {
        this.id = id;
        this.name = name;
        this.id_user = user_id;
        this.id_animal = animal_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}
    public int getUser_id() {
        return id_user;
    }
    public void setUser_id(int id_user) {this.id_user = id_user;}
    public int getAnimal_id() {return id_animal;}
    public void setAnimal_id(int id_animal) {this.id_animal = id_animal;}

}
