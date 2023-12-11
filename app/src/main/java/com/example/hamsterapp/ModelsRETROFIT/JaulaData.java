package com.example.hamsterapp.ModelsRETROFIT;

public class JaulaData {
    String name;
    int animal_id;

    public JaulaData(String name, int animal_id) {
        this.name = name;
        this.animal_id = animal_id;
    }

    public String getName() {
        return name;
    }
    public int getAnimal_id() {
        return animal_id;
    }
}
