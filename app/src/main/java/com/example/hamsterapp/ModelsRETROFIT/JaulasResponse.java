package com.example.hamsterapp.ModelsRETROFIT;

import com.example.hamsterapp.Models.Jaula;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JaulasResponse {
    @SerializedName("Jaulas del usuario")
    private List<Jaula> jaulas;
        public List<Jaula> getJaulas() {

            return jaulas;
        }
}
