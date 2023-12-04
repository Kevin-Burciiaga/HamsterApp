package com.example.hamsterapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.AnimalesAdapter;
import com.example.hamsterapp.Models.Animal;

import java.util.ArrayList;

public class Animales extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Animal> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);

        recyclerView=findViewById(R.id.recyclerviewA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new Animal(R.drawable.huella,"Hamster"));
        arrayList.add(new Animal(R.drawable.huella,"Cuyo"));

        AnimalesAdapter animalesRecyclerView = new AnimalesAdapter(this,arrayList);
        recyclerView.setAdapter(animalesRecyclerView);
    }
}