package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.JaulaAdapter;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.SharedPreferences.Token;
import com.example.hamsterapp.ViewModelss.JaulasViewModel;

import java.util.List;

public class Jaulas extends AppCompatActivity {
private JaulasViewModel viewModel;
    private RecyclerView recyclerView;
    private JaulaAdapter jaulaAdapter;
    ImageView agregar, us, foco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaulas);

        recyclerView = findViewById(R.id.recyclerviewJaulas);
        agregar = findViewById(R.id.agregar);
        foco = findViewById(R.id.foco);

        us = findViewById(R.id.us);
        viewModel = new ViewModelProvider(this).get(JaulasViewModel.class);

        agregar.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, Agregar_Jaula.class);
            startActivity(i);
        });

        foco.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, Animales.class);
            startActivity(i);
        });

        us.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, InfoUser.class);
            startActivity(i);
        });


        viewModel.getJaulaList().observe(this, jaulaList -> {
            if (jaulaList == null || jaulaList.isEmpty()) {
                Toast.makeText(this, "No tienes jaulas", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Agrega una jaula", Toast.LENGTH_SHORT).show();
            } else {
                actualizarLista(jaulaList);
            }
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            mostrarError(errorMessage);
        });
        revisarSesion();
    }
    private void actualizarLista(List<Jaula> jaulaList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerviewJaulas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        jaulaAdapter = new JaulaAdapter(getApplicationContext(), jaulaList);
        recyclerView.setAdapter(jaulaAdapter);
    }
    private void mostrarError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void revisarSesion(){
        if (!Token.getIsLoggedIn(Jaulas.this)){
            Intent intent = new Intent(Jaulas.this,Login.class);
            startActivity(intent);
            finish();
        }
    }

}
