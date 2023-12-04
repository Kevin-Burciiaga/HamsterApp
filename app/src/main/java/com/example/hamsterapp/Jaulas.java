package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.JaulaAdapter;
import com.example.hamsterapp.Models.Jaula;

import java.util.ArrayList;


public class Jaulas extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Jaula> arrayList = new ArrayList<>();

ImageView agregar, us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaulas);
        recyclerView=findViewById(R.id.recyclerview1);
        agregar = findViewById(R.id.agregar);
        us = findViewById(R.id.us);
        agregar.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this,Agregar_Jaula.class);
            startActivity(i);
        });
        us.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this,InfoUser.class);
            startActivity(i);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new Jaula(R.drawable.jaula,"Jaula 1"));

        JaulaAdapter jaulasRecyclerView = new JaulaAdapter(this,arrayList);
        recyclerView.setAdapter(jaulasRecyclerView);


    }
}