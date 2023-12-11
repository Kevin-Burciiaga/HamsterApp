package com.example.hamsterapp.ModelsRETROFIT;

public class JaulaData {
    String name;
    String id_animal;

    public JaulaData(String name, String animal_id) {
        this.name = name;
        this.id_animal = animal_id;
    }

    public String getName() {
        return name;
    }
    public String getAnimal_id() {
        return id_animal;
    }
}
