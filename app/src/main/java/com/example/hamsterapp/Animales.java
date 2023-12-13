package com.example.hamsterapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.AnimalesAdapter;
import com.example.hamsterapp.Models.Animal;
import com.example.hamsterapp.ViewModelss.AnimalesViewModel;

import java.util.List;

public class Animales extends AppCompatActivity {
    private AnimalesViewModel viewModel;
    private RecyclerView recyclerView;
    private AnimalesAdapter animalesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        recyclerView=findViewById(R.id.recyclerviewA);
        viewModel = new ViewModelProvider(this).get(AnimalesViewModel.class);
        viewModel.getAnimalList().observe(this, animalList -> {
            actualizarLista(animalList);
        });
        viewModel.getErrorMessage().observe(this, errorMessage -> {
            mostrarError(errorMessage);
        });

    }

    private void actualizarLista(List<Animal> animalList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerviewA);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        animalesAdapter = new AnimalesAdapter(getApplicationContext(),animalList);
        recyclerView.setAdapter(animalesAdapter);
    }
    private void mostrarError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

}