package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.AnimalesAdapter;
import com.example.hamsterapp.Models.Animal;

import java.util.ArrayList;

public class Animales extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Animal> arrayList = new ArrayList<>();
    ImageView usuario, newanimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        usuario = findViewById(R.id.us);
        newanimal = findViewById(R.id.ag);

        recyclerView=findViewById(R.id.recyclerviewA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new Animal(R.drawable.huella,"Hamster"));
        arrayList.add(new Animal(R.drawable.huella,"Conejo"));

        AnimalesAdapter animalesRecyclerView = new AnimalesAdapter(this,arrayList);
        recyclerView.setAdapter(animalesRecyclerView);

        newanimal.setOnClickListener(v -> {
            Intent intent = new Intent(Animales.this, Agregar_Animal.class);
            startActivity(intent);
        });
    }
}